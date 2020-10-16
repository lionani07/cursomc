package cursomc.resources.handler;

import cursomc.services.exceptions.DataIntegrityViolation;
import cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandarError> resourceNotFound(final ResourceNotFoundException e, HttpServletRequest request) {
        final var standarError = StandarError.of(
                HttpStatus.NOT_FOUND,
                request.getRequestURI(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standarError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolation.class)
    public ResponseEntity<StandarError> dataIntegrityViolation(DataIntegrityViolation e, HttpServletRequest request) {
        final var standarError = StandarError.of(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standarError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> fieldsValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        final var validationError = new ValidationError(
                HttpStatus.BAD_REQUEST,
                request.getRequestURI(),
                e.getBindingResult().getFieldErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
