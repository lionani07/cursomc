package cursomc.resources.dto;

import cursomc.domain.enums.TipoCliente;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
@Getter
public class ClienteDTO {

    private final Integer id;

    @NotBlank
    private final String nome;

    @NotBlank
    @Email
    private final String email;

    private final String cpfOuCnpj;

    private final TipoCliente tipo;

}
