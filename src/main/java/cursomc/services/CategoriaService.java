package cursomc.services;

import cursomc.domain.Categoria;
import cursomc.resources.dto.CategoriaDTO;
import cursomc.respositoires.CategoriaRepository;
import cursomc.services.exceptions.DataIntegrityException;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria find(final Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Categoria.class, id));
    }

    public CategoriaDTO insert(final CategoriaDTO categoriaDTO) {
        return this.repository.save(Categoria.of(categoriaDTO)).toDto();
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
            throw new DataIntegrityException(Categoria.class, e);
        }

    }

    public List<CategoriaDTO> findAll() {
        return this.repository.findAll()
                .stream()
                .map(Categoria::toDto)
                .collect(Collectors.toList());
    }

    public Page<CategoriaDTO> findAllByPage(Pageable pageable) {
        return this.repository.findAll(pageable).map(Categoria::toDto);
    }

    public List<Categoria> findAllByIds(List<Integer> categoriasIds) {
        return this.repository.findAllById(categoriasIds);
    }
}
