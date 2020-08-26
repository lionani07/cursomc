package cursomc.services;

import cursomc.domain.Categoria;
import cursomc.respositoires.CategoriaRepository;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria find(final Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Categoria.class, id));
    }
}
