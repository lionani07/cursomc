package cursomc.resources.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Builder(toBuilder = true)
@Getter
public class FieldMessageError {

    private final String field;
    private final String message;

    public static FieldMessageError of(FieldError fieldError) {
        return FieldMessageError
                .builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }


}
