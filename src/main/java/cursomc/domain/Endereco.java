package cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cursomc.resources.dto.EnderecoNewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public static Endereco of(EnderecoNewDto enderecoNewDto, Cliente cliente, Cidade cidade) {
        return Endereco
                .builder()
                .logradouro(enderecoNewDto.getLogradouro())
                .numero(enderecoNewDto.getNumero())
                .complemento(enderecoNewDto.getComplemento())
                .bairro(enderecoNewDto.getBairro())
                .cep(enderecoNewDto.getCep())
                .cliente(cliente)
                .cidade(cidade)
                .build();
    }
}
