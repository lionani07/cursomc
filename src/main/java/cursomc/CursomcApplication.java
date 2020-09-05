package cursomc;

import cursomc.domain.*;
import cursomc.domain.enums.EstadoPagamento;
import cursomc.domain.enums.TipoCliente;
import cursomc.respositoires.*;
import cursomc.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		insertIntoCategoriaProduto();
		insertIntoCidadeEstado();
		insertIntoClienteEndereco();
		insertPedidos();
	}

	private void insertIntoCategoriaProduto() {
		var categoria1 = Categoria.builder().nome("Informática").build();
		var categoria2 = Categoria.builder().nome("Escritório").build();

		var produto1 = Produto
				.builder()
				.nome("Computador")
				.valor(2000.00)
				.build();

		var produto2 = Produto
				.builder()
				.nome("Impressora")
				.valor(800.00)
				.build();

		var produto3 = Produto
				.builder()
				.nome("Mouse")
				.valor(80.00)
				.build();

		categoria1.getProdutos().addAll(List.of(produto1, produto2, produto3));
		categoria2.getProdutos().add(produto2);

		produto1.getCategorias().add(categoria1);
		produto2.getCategorias().addAll(List.of(categoria1, categoria2));
		produto3.getCategorias().add(categoria1);

		this.categoriaRepository.saveAll(List.of(categoria1, categoria2));
		this.produtoRepository.saveAll(List.of(produto1, produto2, produto3));

	}

	private void insertIntoCidadeEstado() {
		Estado minas = Estado
				.builder()
				.nome("Minas Gerais")
				.build();

		Estado sp = Estado
				.builder()
				.nome("Sao Paulo")
				.build();

		Cidade uberlandia = Cidade
				.builder()
				.nome("Uberlandia")
				.estado(minas)
				.build();

		Cidade taubate = Cidade
				.builder()
				.nome("Taubaté")
				.estado(sp)
				.build();

		Cidade campinas = Cidade
				.builder()
				.nome("Campinas")
				.estado(sp)
				.build();

		sp.getCidades().addAll(List.of(campinas, taubate));
		minas.getCidades().add(uberlandia);

		this.estadoRepository.saveAll(List.of(minas, sp));
		this.cidadeRepository.saveAll(List.of(uberlandia, taubate, campinas));

	}

	private void insertIntoClienteEndereco() {
		Cliente  cliente = Cliente
				.builder()
				.nome("Maria Silva")
				.email("maria@gmail.com")
				.cpfOuCnpj("14898367488")
				.tipo(TipoCliente.PESSOAFISICA)
				.telefones(Set.of("27363323", "75843562"))
				.build();

		Endereco endereco1 = Endereco
				.builder()
				.cliente(cliente)
				.logradouro("Rua Flores")
				.numero("300")
				.complemento("Apto 203")
				.bairro("Jardim")
				.cep("38220836")
				.cidade(this.cidadeRepository.getOne(1))
				.build();

		Endereco endereco2 = Endereco
				.builder()
				.cliente(cliente)
				.logradouro("Avenida Matos")
				.numero("105")
				.complemento("Sala 800")
				.bairro("Centro")
				.cep("38777022")
				.cidade(this.cidadeRepository.getOne(2))
				.build();

		cliente.getEnderecos().addAll(List.of(endereco1, endereco2));

		this.clienteRepository.save(cliente);
		this.enderecoRepository.saveAll(List.of(endereco1, endereco2));

	}

	private void insertPedidos() {
		final var cliente = this.clienteRepository.findById(1).get();
		final var enderecoDeEntrega = this.enderecoRepository.findById(1).get();


		final var pedido1 = Pedido
				.builder()
				.instante(LocalDateTime.now().minusDays(1))
				.cliente(cliente)
				.enderecoDeEntrega(enderecoDeEntrega)
				.build();

		final var pedido2 = Pedido
				.builder()
				.instante(LocalDateTime.now())
				.cliente(cliente)
				.enderecoDeEntrega(enderecoDeEntrega)
				.build();


		final var pagamentoPedido1 = PagamentoComCartao
				.builder()
				.pedido(pedido1)
				.estado(EstadoPagamento.QUITADO)
				.numeroDeParcelas(6)
				.build();

		final var pagamentoPedido2 = PagamentoComBoleto
				.builder()
				.pedido(pedido2)
				.estado(EstadoPagamento.PEDENTE)
				.dateVencimento(LocalDate.now().plusMonths(2))
				.build();

		pedido1.setPagamento(pagamentoPedido1);
		pedido2.setPagamento(pagamentoPedido2);

		this.pedidoRepository.saveAll(List.of(pedido1, pedido2));
		this.pagamentoRepository.saveAll(List.of(pagamentoPedido1, pagamentoPedido2));

		this.pedidoRepository.findAllByCliente(cliente)
				.forEach(System.out::println);

	}
}
