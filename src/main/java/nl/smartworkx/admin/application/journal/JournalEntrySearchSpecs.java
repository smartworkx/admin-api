package nl.smartworkx.admin.application.journal;


import static nl.smartworkx.admin.infrastructure.jpa.SearchSpecs.getPath;
import static nl.smartworkx.admin.infrastructure.jpa.SearchSpecs.greaterThanOrEqualTo;
import static nl.smartworkx.admin.infrastructure.jpa.SearchSpecs.lessThan;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;
import nl.smartworkx.admin.model.journal.JournalEntry;


/**
 * Confirmed Operation search specifications
 */
final class JournalEntrySearchSpecs {

    private static final String VALUE_DATE_PROPERTY_NAME = "valueDate";

    static Specification<JournalEntry> hasValueDateGreaterThanOrEqualToDate(LocalDate expectedDate) {
        return expectedDate != null ? greaterThanOrEqualTo(expectedDate, VALUE_DATE_PROPERTY_NAME) : null;
    }

    static Specification<JournalEntry> hasValueDateLessThanDate(LocalDate expectedDate) {
        return expectedDate != null ? lessThan(expectedDate, VALUE_DATE_PROPERTY_NAME) : null;
    }

    static Specification<JournalEntry> hasLedger(List<Long> ledgers) {
        return !ledgers.isEmpty() ? (root, query, builder) -> {
            final Join<Object, Object> records = root.join("records", JoinType.INNER);
            final Expression path = getPath(records, builder, "ledgerId");
            return path.in(ledgers);
        } : null;
    }
}
