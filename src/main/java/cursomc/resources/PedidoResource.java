package cursomc.resources;

import cursomc.domain.Pedido;
import cursomc.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoResource {

    private final PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        final var pedido = this.pedidoService.find(id);
        return ResponseEntity.ok().body(pedido);
    }

}
