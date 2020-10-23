package cursomc.services;

import cursomc.domain.Cliente;
import cursomc.resources.dto.ClienteDTO;
import cursomc.respositoires.ClienteRepository;
import cursomc.respositoires.PedidoRepository;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final PedidoRepository pedidoRepository;

    public ClienteDTO find(Integer id) {
        return this.repository
                .findById(id)
                .map(Cliente::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(Cliente.class, id));
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        return this.repository.save(Cliente.of(clienteDTO)).toDto();
    }

    public List<ClienteDTO> findAll() {
        return this.repository
                .findAll()
                .stream()
                .map(Cliente::toDto)
                .collect(Collectors.toList());
    }
}
