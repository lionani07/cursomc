package cursomc.respositoires;

import cursomc.domain.Categoria;
import cursomc.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
