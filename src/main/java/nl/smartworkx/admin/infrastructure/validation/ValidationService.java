package nl.smartworkx.admin.infrastructure.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Service to validate objects.
 */
@Service
@Slf4j
public class ValidationService {

    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * @param object Object to validate.
     */
    public void validate(Object object) {
        final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            log.info("Object {} did not meet message constraints. Throwing a ValidationException.", object);
            throw ValidationException.builder().constraintViolations(constraintViolations).build();
        }
    }
}