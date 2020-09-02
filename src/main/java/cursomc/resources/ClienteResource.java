package cursomc.resources;

import cursomc.domain.Cliente;
import cursomc.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        final var cliente = this.clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }
}
