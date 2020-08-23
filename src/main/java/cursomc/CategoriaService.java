package cursomc;

import cursomc.domain.Categoria;
import cursomc.respositoires.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria find(final Integer id) {
        return this.repository.findById(id).orElse(null);
    }
}
