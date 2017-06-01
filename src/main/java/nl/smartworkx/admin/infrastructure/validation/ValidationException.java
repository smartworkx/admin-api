package nl.smartworkx.admin.infrastructure.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Builder;

/**
 * Exception to throw when validation fails.
 */
@Builder
@AllArgsConstructor
@Getter
@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {
    private Set<ConstraintViolation<Object>> constraintViolations;
}
