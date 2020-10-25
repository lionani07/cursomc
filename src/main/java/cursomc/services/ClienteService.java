package cursomc.services;

import cursomc.domain.Cliente;
import cursomc.resources.dto.ClienteDTO;
import cursomc.respositoires.ClienteRepository;
import cursomc.respositoires.PedidoRepository;
import cursomc.services.exceptions.DataIntegrityException;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final PedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public ClienteDTO find(Integer id) {
        return this.repository
                .findById(id)
                .map(Cliente::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(Cliente.class, id));
    }

    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO) {
        return this.repository.save(Cliente.of(clienteDTO)).toDto();
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable) {
        return this.repository
                .findAll(pageable)
                .map(Cliente::toDto);
    }

    @Transactional
    public void delete(Integer id) {
        try {
            this.repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(Cliente.class, id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(Cliente.class, e);
        }
    }

    @Transactional
    public ClienteDTO update(Integer id, ClienteDTO clienteDTO) {
        final var exintingCliente = find(id);
        final var clienteToUpdate = clienteDTO
                .toBuilder()
                .id(exintingCliente.getId())
                .build();
        return this.repository.save(Cliente.of(clienteToUpdate)).toDto();
    }

}
