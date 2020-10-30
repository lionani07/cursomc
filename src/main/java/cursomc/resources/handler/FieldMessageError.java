package cursomc.resources.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class FieldMessageError {

    private final String field;
    private final String message;
}
