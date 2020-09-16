package cursomc.resources.dto;

import cursomc.domain.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CategoriaDTO {

    private String nome;
    private Integer totalProdutos;

    public CategoriaDTO(Categoria categoria) {
        this.nome = categoria.getNome();
        this.totalProdutos = categoria.getTotalProdutos();
    }

    public static List<CategoriaDTO> of(List<Categoria> categorias) {
        return categorias
                .stream()
                .map(CategoriaDTO::new)
                .collect(Collectors.toList());
    }

}
