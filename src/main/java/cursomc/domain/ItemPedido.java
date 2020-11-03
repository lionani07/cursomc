package cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class ItemPedido {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {}

    public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, Double desconto) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = produto.getValor();
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setPedido(Pedido pedido) {
        this.id.setPedido(pedido);
    }

    public void setProduto(Produto produto) {
        this.id.setProduto(produto);
    }

    @JsonIgnore
    public Pedido getPedido() {
        return this.id.getPedido();
    }

    public Double getTotal() {
        return this.preco * this.quantidade;
    }
}
