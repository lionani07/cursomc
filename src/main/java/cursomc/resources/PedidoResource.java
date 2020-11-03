package cursomc.resources;

import cursomc.domain.Pedido;
import cursomc.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Pedido pedido, UriComponentsBuilder uriComponentsBuilder) {
        final var pedidoSaved = this.pedidoService.save(pedido);

        final var location = uriComponentsBuilder
                .path("/{id}")
                .buildAndExpand(pedidoSaved.getId())
                .toUri();

        return ResponseEntity.created(location).body(pedidoSaved);

    }

}
