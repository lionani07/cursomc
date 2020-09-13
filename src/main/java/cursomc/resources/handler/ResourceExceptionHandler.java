package cursomc.resources.handler;

import cursomc.services.exceptions.DataIntegrityViolation;
import cursomc.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    private final static HttpStatus NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;
    private final static HttpStatus BAD_REQUEST_STATUS = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandarError> resourceNotFound(final ResourceNotFoundException e, HttpServletRequest request) {

        var standarError = StandarError
                .builder()
                .path(request.getRequestURI())
                .httpStatus(NOT_FOUND_STATUS)
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(NOT_FOUND_STATUS).body(standarError);
    }

    @ExceptionHandler(DataIntegrityViolation.class)
    public ResponseEntity<StandarError> dataIntegrityViolation(DataIntegrityViolation e, HttpServletRequest request) {
        final var standarError = StandarError
                .builder()
                .path(request.getRequestURI())
                .httpStatus(BAD_REQUEST_STATUS)
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(BAD_REQUEST_STATUS).body(standarError);
    }
}
