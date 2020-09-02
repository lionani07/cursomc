package cursomc.services;

import cursomc.domain.Cliente;
import cursomc.respositoires.ClienteRepository;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente find(Integer id) {
        return this.repository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Cliente.class, id));
    }
}
