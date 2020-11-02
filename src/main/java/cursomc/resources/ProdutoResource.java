package cursomc.resources;

import cursomc.resources.dto.ProdutoDTO;
import cursomc.resources.utils.URL;
import cursomc.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoResource {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> search(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            Pageable pageable) {

        final var categoriasIds = URL.convertFrom(categorias);

        final var produtoPage = this.produtoService.search(nome, categoriasIds, pageable);
        return ResponseEntity.ok(produtoPage);
    }
}
