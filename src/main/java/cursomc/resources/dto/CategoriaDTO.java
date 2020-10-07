package cursomc.resources.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
@Getter
public class CategoriaDTO {

    private Integer id;

    @NotBlank(message = "Nome é campo obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho do nome deve estar entre 5 - 80 caracteres")
    private String nome;

}
