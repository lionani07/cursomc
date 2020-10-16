package cursomc.resources.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder(toBuilder = true)
public class ValidationError extends StandarError {

    private static final String MESSAGE_ERROR = "Validations errors";

    @Builder.Default
    private List<FieldMessageError> fieldMessageErrors = new ArrayList<>();

    public ValidationError(HttpStatus httpStatus, String path, List<FieldError> fieldErrors) {
        this.setHttpStatus(httpStatus);
        this.setPath(path);
        this.setMessage(MESSAGE_ERROR);
        this.setTime(LocalDateTime.now());
        this.fieldMessageErrors = convert(fieldErrors);
    }

    private List<FieldMessageError> convert(final List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map(FieldMessageError::of)
                .collect(Collectors.toList());
    }


}
