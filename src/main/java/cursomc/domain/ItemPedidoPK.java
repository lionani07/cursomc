package cursomc.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"pedido", "produto"})
public class ItemPedidoPK implements Serializable {

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

}
