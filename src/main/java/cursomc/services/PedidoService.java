package cursomc.services;

import cursomc.domain.Pedido;
import cursomc.respositoires.PedidoRepository;
import cursomc.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public Pedido find(final Integer id) {
        return this
                .repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Pedido.class, id));
    }
}
