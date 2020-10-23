package cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cursomc.domain.enums.TipoCliente;
import cursomc.resources.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @OneToMany(mappedBy = "cliente")
    private final List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public static Cliente of(ClienteDTO clienteDTO) {
        return Cliente
                .builder()
                .id(clienteDTO.getId())
                .nome(clienteDTO.getNome())
                .email(clienteDTO.getNome())
                .cpfOuCnpj(clienteDTO.getCpfOuCnpj())
                .tipo(clienteDTO.getTipo())
                .build();
    }

    public ClienteDTO toDto() {
        return ClienteDTO
                .builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .cpfOuCnpj(this.cpfOuCnpj)
                .tipo(this.tipo)
                .build();
    }

}
