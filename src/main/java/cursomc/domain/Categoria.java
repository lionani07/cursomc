package cursomc.domain;

import lombok.*;

@Builder(toBuilder = true)
@Value
@EqualsAndHashCode(of = "id")
public class Categoria {

    private Integer id;
    private String nome;
}
