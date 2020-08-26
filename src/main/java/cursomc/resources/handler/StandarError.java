package cursomc.resources.handler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class StandarError {

    private final String path;
    private final HttpStatus httpStatus;
    private final String message;
    private final LocalDateTime time = LocalDateTime.now();

}
