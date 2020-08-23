package cursomc.resources;

import cursomc.CategoriaService;
import cursomc.domain.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaResource {

    private final CategoriaService service;

    @GetMapping("/{id}")
    public Categoria find(@PathVariable final Integer id) {
        return this.service.find(id);
    }
}
