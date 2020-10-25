package cursomc.resources.dto;

import cursomc.domain.enums.TipoCliente;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class ClienteNewDTO {

    private final String nome;
    private final String email;
    private final String cpfOuCnpj;
    private final TipoCliente tipo;

    private final List<EnderecoNewDto> enderecos = new ArrayList<>();
    private final String telefone;
}
