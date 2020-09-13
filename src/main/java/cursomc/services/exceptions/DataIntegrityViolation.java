package cursomc.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrityViolation extends RuntimeException {
    public DataIntegrityViolation(Class<?> clazz, DataIntegrityViolationException e) {
        super(String.format("Can´t delete %s. Not null property references", clazz.getSimpleName()), e);
    }
}
