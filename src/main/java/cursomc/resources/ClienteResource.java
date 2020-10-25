package cursomc.resources;

import cursomc.resources.dto.ClienteDTO;
import cursomc.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Integer id) {
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable final Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        final var clienteUpdated = this.clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(clienteUpdated);
    }

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2")  int size,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        final var sort = Sort.by(Sort.Direction.valueOf(direction), orderBy);
        final var pageable = PageRequest.of(page, size, sort);

        final var pageCliente = this.clienteService.findAll(pageable);
        return ResponseEntity.ok(pageCliente);
    }


}
