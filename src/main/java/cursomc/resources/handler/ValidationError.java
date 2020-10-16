package cursomc.resources.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
public class ValidationError extends StandarError {

    private static final String MESSAGE_ERROR = "Validations errors";

    @Builder.Default
    private List<FieldMessageError> fieldMessageErrors = new ArrayList<>();

    public ValidationError(HttpStatus httpStatus, String path, List<FieldMessageError> fieldMessageErrors) {
        this.setHttpStatus(httpStatus);
        this.setPath(path);
        this.setMessage(MESSAGE_ERROR);
        this.setTime(LocalDateTime.now());
        this.fieldMessageErrors = fieldMessageErrors;
    }

}
