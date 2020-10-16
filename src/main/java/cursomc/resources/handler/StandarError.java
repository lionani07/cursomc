package cursomc.resources.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class StandarError {

    private String path;
    private HttpStatus httpStatus;
    private String message;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime time;

    public static StandarError of(HttpStatus httpStatus, String path, String message) {
        return StandarError
                .builder()
                .httpStatus(httpStatus)
                .path(path)
                .message(message)
                .time(LocalDateTime.now())
                .build();

    }

}
