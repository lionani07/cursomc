package cursomc.respositoires;

import cursomc.domain.Pagamento;
import cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
