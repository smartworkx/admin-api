package nl.smartworkx.admin.infrastructure.jpa;

import static java.util.Arrays.copyOfRange;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

/**
 * Generic search specs for searching domain entities.
 */
public final class SearchSpecs {

    /**
     *
     * @param value Value to equal with, can be null then no predicate is returned.
     * @param properties To filter on.
     * @param <T> Type for which the query is.
     * @return A predicate when the parameter value is not empty.
     */
    public static <T> Specification<T> equal(final Object value, final String... properties) {
        return (root, query, builder) -> value == null ? null : builder.equal(getPath(root, builder, properties), value);
    }

    /**
     * Returns a specification which tests whether the value found at the given property path evaluates to false or does not exist.
     *
     * @param properties Properties representing the expression path. Leaf property is assumed to be of type boolean.
     * @param <T> Type for which the query is.
     * @return Specification for testing the value being false.
     */
    public static <T> Specification<T> isFalse(final String... properties) {
        return (root, query, builder) -> {
            final Expression<Boolean> path = getPath(root, builder, properties).as(Boolean.class);
            return builder.or(builder.isFalse(path), builder.isNull(path));
        };
    }

    /**
     *
     * @param values List of values to equal with, can be null then no predicate is returned.
     * @param properties To filter on.
     * @param <T> Type for which the query is.
     * @return A predicate when the parameter value is not empty.
     */
    public static <T> Specification<T> in(final List values, final String... properties) {
        return (root, query, builder) -> values == null || values.isEmpty() ? null : getPath(root, builder, properties).in(values);
    }

    /**
     *
     * @param valueFrom Value to compare with, can be null then no predicate is returned.
     * @param properties To filter on.
     * @param <T> Type for which the query is.
     * @return A predicate when the parameter value is not empty.
     */
    public static <T> Specification<T> greaterThanOrEqualTo(BigDecimal valueFrom, String... properties) {
        return (root, query, builder) ->
                valueFrom == null ? null : builder.greaterThanOrEqualTo(getPath(root, builder, properties).as(BigDecimal.class), valueFrom);
    }

    /**
     *
     * @param localDate Local date to compare with, can be null in which case the predicate returns null.
     * @param properties Properties representing the expression path. Leaf property is assumed to be of type LocalDate.
     * @param <T> Type for which the query is.
     * @return Specification instance, cannot be null
     */
    public static <T> Specification<T> greaterThanOrEqualTo(LocalDate localDate, String... properties) {
        return (root, query, builder) ->
                localDate == null ? null : builder.greaterThanOrEqualTo(getPath(root, builder, properties).as(LocalDate.class), localDate);
    }

    /**
     *
     * @param zonedDateTime Zoned date time to compare with, can be null in which case the predicate returns null.
     * @param properties Properties representing the expression path. Leaf property is assumed to be of type ZonedDateTime.
     * @param <T> Type for which the query is.
     * @return Specification instance, cannot be null
     */
    public static <T> Specification<T> greaterThanOrEqualTo(ZonedDateTime zonedDateTime, String... properties) {
        return zonedDateTime != null ? (root, query, builder) ->
                builder.greaterThanOrEqualTo(getPath(root, builder, properties).as(Timestamp.class), Timestamp.from(zonedDateTime.toInstant())) : null;
    }

    /**
     *
     * @param bigDecimal Big decimal to compare with, can be null in which case the predicate returns null.
     * @param properties Properties representing the expression path. Leaf property is assumed to be of type BigDecimal.
     * @param <T> Type for which the query is.
     * @return Specification instance, cannot be null
     */
    public static <T> Specification<T> lessThanOrEqualTo(BigDecimal bigDecimal, String... properties) {
        return bigDecimal
                != null ? (root, query, builder) -> builder.lessThanOrEqualTo(getPath(root, builder, properties).as(BigDecimal.class), bigDecimal) : null;
    }

    /**
     *
     * @param zonedDateTime Zoned date time to compare with, can be null in which case the predicate returns null.
     * @param properties Properties representing the expression path. Leaf property is assumed to be of type ZonedDateTime.
     * @param <T> Type for which the query is.
     * @return Specification instance, cannot be null
     */
    public static <T> Specification<T> lessThanOrEqualTo(ZonedDateTime zonedDateTime, String... properties) {
        return zonedDateTime != null ? (root, query, builder) ->
                builder.lessThanOrEqualTo(getPath(root, builder, properties).as(Timestamp.class), Timestamp.from(zonedDateTime.toInstant())) : null;
    }

    /**
     *
     * @param localDate Value to compare with, can be null then no predicate is returned.
     * @param properties To filter on.
     * @param <T> Type for which the query is.
     * @return A predicate when the parameter value is not empty.
     */
    public static <T> Specification<T> lessThan(LocalDate localDate, String... properties) {
        return (root, query, builder) -> localDate == null ? null : builder.lessThan(getPath(root, builder, properties).as(LocalDate.class), localDate);
    }

    /**
     * Returns a path as expression possibly nested.
     *
     * @param path starting point
     * @param builder criteria builder
     * @param properties Properties representing the expression path.
     * @return the expression found
     */
    public static Expression<?> getPath(Path<?> path, CriteriaBuilder builder, String... properties) {
        if (properties.length == 1) {
            final Path<Object> objectPath = path.get(properties[0]);
            return objectPath;
        } else {
            return getPath(path.get(properties[0]), builder, copyOfRange(properties, 1, properties.length));
        }
    }

    /**
     * Searches by string and like case insensitive.
     *
     * @param values The string values.
     * @param properties The property to filter on.
     * @return The specification or null.
     */
    public static <T> Specification<T> like(List<String> values, String... properties) {
        Specifications<T> specifications = null;
        if (values != null) {
            for (String value : values) {
                if (specifications == null) {
                    specifications = where(like(value, properties));
                } else {
                    specifications = specifications.or(like(value, properties));
                }
            }
            return specifications;
        }
        return null;
    }

    /**
     *
     * @param value Value to compare with (case-insensitive), can be null then no predicate is returned.
     * @param properties To filter on.
     * @param <T> Type for which the query is.
     * @return A predicate when the parameter value is not empty.
     */
    public static <T> Specification<T> like(final String value, final String... properties) {
        return (root, query, builder) ->
                value == null ? null : builder.like(builder.lower((Expression<String>) getPath(root, builder, properties)), "%" + value.toLowerCase() +
                        "%");
    }

    public static <T> Specification<T> isNull(final String... properties) {
        return (root, query, builder) -> builder.isNull(getPath(root, builder, properties));
    }

    public static <T> Specification<T> isNotNull(final String... properties) {
        return (root, query, builder) -> builder.isNotNull(getPath(root, builder, properties));
    }
}
