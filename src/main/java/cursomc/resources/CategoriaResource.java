package cursomc.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Value("${server.port}")
    private String port;

    @GetMapping
    public String listar() {
        return "catergorias no port: " + port;
    }
}
