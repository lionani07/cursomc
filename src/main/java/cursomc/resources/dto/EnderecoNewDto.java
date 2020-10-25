package cursomc.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class EnderecoNewDto {

    private final Integer cidadeId;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cep;
}
