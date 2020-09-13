package cursomc.resources.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime time = LocalDateTime.now();

}
