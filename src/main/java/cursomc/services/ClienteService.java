package cursomc.services;

import cursomc.domain.Cliente;
import cursomc.domain.Endereco;
import cursomc.resources.dto.ClienteDTO;
import cursomc.resources.dto.ClienteNewDTO;
import cursomc.respositoires.CidadeRepository;
import cursomc.respositoires.ClienteRepository;
import cursomc.respositoires.EnderecoRepository;
import cursomc.services.exceptions.DataIntegrityException;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoRepository enderecoRepository;

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

    @Transactional
    public ClienteDTO save(ClienteNewDTO clienteNewDTO) {
        final var clienteSaved = this.repository.save(Cliente.of(clienteNewDTO));

        final var enderecos = buildEnderecosFrom(clienteNewDTO, clienteSaved);

        this.enderecoRepository.saveAll(enderecos);

        return clienteSaved.toDto();

    }

    private List<Endereco> buildEnderecosFrom(ClienteNewDTO clienteNewDTO, Cliente cliente) {
        return clienteNewDTO.getEnderecos()
                .stream()
                .map(enderecoNewDto -> {
                    final var cidade = this.cidadeRepository.getOne(enderecoNewDto.getCidadeId());
                    return Endereco.of(enderecoNewDto, cliente, cidade);
                }).collect(Collectors.toList());
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
