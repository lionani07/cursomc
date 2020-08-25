package cursomc;

import cursomc.domain.Categoria;
import cursomc.respositoires.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CursomcApplication implements CommandLineRunner {

	private final CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var categoria1 = Categoria.builder().nome("Informática").build();
		var categoria2 = Categoria.builder().nome("Escritório").build();

		this.categoriaRepository.saveAll(List.of(categoria1, categoria2));

	}
}
