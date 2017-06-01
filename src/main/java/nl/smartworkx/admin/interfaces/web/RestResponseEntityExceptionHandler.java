package nl.smartworkx.admin.interfaces.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import nl.smartworkx.admin.infrastructure.validation.ValidationError;
import nl.smartworkx.admin.infrastructure.validation.ValidationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler({ ValidationException.class })
    public ResponseEntity<Object> handleValidationException(
            Exception ex, WebRequest request) {
        ValidationException nevEx =
                (ValidationException) ex;

        List<ValidationError> errors = nevEx.getConstraintViolations()
                .stream()
                .map(violation -> new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<Object>(errors, new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}