package cursomc.resources;

import cursomc.domain.Categoria;
import cursomc.resources.dto.CategoriaDTO;
import cursomc.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable final Integer id) {
        final var categoria = this.service.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody final Categoria categoria) {
        final var categoriaCreated = this.service.insert(categoria);
        final var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        this.service.update(id, categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        final var categorias = this.service.findAll();
        return ResponseEntity.ok().body(CategoriaDTO.of(categorias));
    }

    @GetMapping("/pagination")
    public Page<CategoriaDTO> findAllWithPagination(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "nome") String orderBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        final var categorias = this.service.findAllByPage(pageable);
        return categorias.map(CategoriaDTO::new);
    }
}
