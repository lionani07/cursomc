package cursomc.services;

import cursomc.domain.ItemPedido;
import cursomc.domain.Pedido;
import cursomc.domain.enums.EstadoPagamento;
import cursomc.respositoires.ItemPedidoRepository;
import cursomc.respositoires.PagamentoRepository;
import cursomc.respositoires.PedidoRepository;
import cursomc.respositoires.ProdutoRepository;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ProdutoRepository produtoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public Pedido find(final Integer id) {
        return this
                .repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Pedido.class, id));
    }

    public Pedido save(Pedido pedido) {
        pedido.setInstante(LocalDateTime.now());
        pedido.getPagamento().setPedido(pedido);
        pedido.getPagamento().setEstado(EstadoPagamento.PEDENTE);

        final var pedidoSaved =  this.repository.save(pedido);
        this.pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido itemPedido : pedido.getItens()) {
           final var produto = this.produtoRepository.findById(itemPedido.getProduto().getId());
           itemPedido.setPedido(pedido);
           itemPedido.setPreco(produto.get().getValor());
           itemPedido.setDesconto(0.0);
        }

        this.itemPedidoRepository.saveAll(pedido.getItens());
        return pedidoSaved;
    }

}
