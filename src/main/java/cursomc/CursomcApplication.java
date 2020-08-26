package cursomc;

import cursomc.domain.Categoria;
import cursomc.domain.Produto;
import cursomc.respositoires.CategoriaRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

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

		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoria2.getProdutos().add(produto2);

		produto1.getCategorias().add(categoria1);
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().add(categoria1);

		this.categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		this.produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

	}
}
