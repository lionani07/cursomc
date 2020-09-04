package cursomc.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPagamento {

    PEDENTE("Pendente"),
    QUITADO("Quitado"),
    CANCELADO("Cancelado");

    private final String descricao;
}
