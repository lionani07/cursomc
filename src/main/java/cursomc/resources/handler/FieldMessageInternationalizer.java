package cursomc.resources.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldMessageInternationalizer {

    private final MessageSource messageSource;

    public List<FieldMessageError> convertAndInternationalize(List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map(fieldError -> new FieldMessageError(fieldError.getField(), internationalizeMessage(fieldError)))
                .collect(Collectors.toList());
    }

    private String internationalizeMessage(final FieldError fieldError) {
        return this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
    }

}
