package cursomc.services;

import cursomc.domain.Categoria;
import cursomc.respositoires.CategoriaRepository;
import cursomc.services.exceptions.DataIntegrityViolation;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria find(final Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Categoria.class, id));
    }

    public Categoria insert(final Categoria categoria) {
        return this.repository.save(categoria);
    }

    public void update(Integer id, Categoria categoria) {
        final var categoriaToUpdate = this.find(id);
        categoria.setId(categoriaToUpdate.getId());
        this.repository.save(categoria);
    }

    public void delete(final Integer id) {
        try {
            this.find(id);
            this.repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolation(Categoria.class, e);
        }

    }

    public List<Categoria> findAll() {
        return this.repository.findAll();
    }

    public Page<Categoria> findAllByPage(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
