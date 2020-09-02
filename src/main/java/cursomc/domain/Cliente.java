package cursomc.domain;

import cursomc.domain.enums.TipoCliente;
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

    //private final Set<String> telefones = new HashSet<>();

}
