package cursomc.services;

import cursomc.domain.Cliente;
import cursomc.domain.Pedido;
import cursomc.respositoires.ClienteRepository;
import cursomc.respositoires.PedidoRepository;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final PedidoRepository pedidoRepository;

    public Cliente find(Integer id) {
        return this.repository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Cliente.class, id));
    }
}
