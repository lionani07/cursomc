package cursomc;

import cursomc.domain.Categoria;
import cursomc.domain.Cidade;
import cursomc.domain.Estado;
import cursomc.domain.Produto;
import cursomc.respositoires.CategoriaRepository;
import cursomc.respositoires.CidadeRepository;
import cursomc.respositoires.EstadoRepository;
import cursomc.respositoires.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		insertIntoCategoriaProduto();
		insertIntoCidadeEstado();
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
}
