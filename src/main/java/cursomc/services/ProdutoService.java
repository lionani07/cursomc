package cursomc.services;

import cursomc.domain.Produto;
import cursomc.resources.dto.ProdutoDTO;
import cursomc.respositoires.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    public Page<ProdutoDTO> search(String nome, List<Integer> categoriasIds, Pageable pageable) {
        final var categorias = this.categoriaService.findAllByIds(categoriasIds);
        return this.produtoRepository
                .findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageable)
                .map(Produto::toDto);
    }

}
