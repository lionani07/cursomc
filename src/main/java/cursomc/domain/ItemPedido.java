package cursomc.domain;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPK id;

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {}

    public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco, Double desconto) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public Pedido getPedido() {
        return this.id.getPedido();
    }
}
