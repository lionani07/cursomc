package cursomc.resources;

import cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> listar() {
        Categoria categoria1 = Categoria.builder().id(1).nome("Informática").build();
        Categoria categoria2 = Categoria.builder().id(2).nome("Escritório").build();

        return List.of(categoria1, categoria2);

    }
}
