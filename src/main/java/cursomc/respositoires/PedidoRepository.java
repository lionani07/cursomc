package cursomc.respositoires;

import cursomc.domain.Cliente;
import cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    public List<Pedido> findAllByCliente(Cliente cliente);
}
