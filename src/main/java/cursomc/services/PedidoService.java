package cursomc.services;

import cursomc.domain.ItemPedido;
import cursomc.domain.Pedido;
import cursomc.domain.enums.EstadoPagamento;
import cursomc.respositoires.*;
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
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final EmailService emailService;

    public Pedido find(final Integer id) {
        return this
                .repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Pedido.class, id));
    }


    //TODO: refactorar chamadas de repositories e services aqui.
    public Pedido save(Pedido pedido) {
        pedido.setInstante(LocalDateTime.now());
        pedido.getPagamento().setPedido(pedido);
        pedido.getPagamento().setEstado(EstadoPagamento.PEDENTE);
        // TODO: deletar, so foi ussado para fines didacticos.
        pedido.setCliente(this.clienteRepository.findById(pedido.getCliente().getId()).orElse(null));
        pedido.setEnderecoDeEntrega(this.enderecoRepository.findById(pedido.getEnderecoDeEntrega().getId()).orElse(null));

        final var pedidoSaved =  this.repository.save(pedido);
        this.pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido itemPedido : pedido.getItens()) {
           final var produto = this.produtoRepository.findById(itemPedido.getProduto().getId()).orElse(null);
           itemPedido.setPedido(pedido);
           itemPedido.setProduto(produto);
           itemPedido.setPreco(itemPedido.getProduto().getValor());
           itemPedido.setDesconto(0.0);
        }

        this.itemPedidoRepository.saveAll(pedido.getItens());
        this.emailService.sendOrderConfirmation(pedido);
        return pedidoSaved;
    }

}
