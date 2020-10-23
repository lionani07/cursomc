package cursomc.resources;

import cursomc.resources.dto.ClienteDTO;
import cursomc.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> find(@PathVariable Integer id) {
        final var cliente = this.clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@RequestBody @Valid ClienteDTO clienteDTO, UriComponentsBuilder uriComponentsBuilder) {
        final var clienteSavedAsDto = this.clienteService.save(clienteDTO);

        final var location = uriComponentsBuilder
                .path("/{id}")
                .buildAndExpand(clienteSavedAsDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(clienteSavedAsDto);
    }


}
