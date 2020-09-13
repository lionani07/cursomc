package cursomc.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(final Class<?> clazz, final Integer id) {
        super(String.format("Resource %s not found with id: %s", clazz.getSimpleName(), id));
    }
}
