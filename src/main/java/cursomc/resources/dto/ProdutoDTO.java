package cursomc.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private Double valor;
}
