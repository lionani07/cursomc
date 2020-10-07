package cursomc.domain;

import cursomc.resources.dto.CategoriaDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private final List<Produto> produtos = new ArrayList<>();

    public Integer getTotalProdutos() {
        return this.produtos.size();
    }

    public static Categoria of(CategoriaDTO categoriaDTO) {
        return Categoria
                .builder()
                .id(categoriaDTO.getId())
                .nome(categoriaDTO.getNome())
                .build();
    }

    public CategoriaDTO toDto() {
        return CategoriaDTO
                .builder()
                .id(this.id)
                .nome(this.nome)
                .build();
    }
}
