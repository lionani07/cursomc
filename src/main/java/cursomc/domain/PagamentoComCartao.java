package cursomc.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder
public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;
}
