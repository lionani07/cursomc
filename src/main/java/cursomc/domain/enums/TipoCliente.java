package cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.util.Arrays.stream;

@Getter
@AllArgsConstructor
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private final int codigo;
    private final String descricao;

    public static TipoCliente getByCodigo(final int codigo) {
        return stream(values())
                .filter(tipo -> tipo.codigo == codigo)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Tipo de cliente invalido para codigo: " + codigo));
    }
}
