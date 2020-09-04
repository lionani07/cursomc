package cursomc.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@SuperBuilder
public class PagamentoComBoleto extends Pagamento {

    private LocalDate dateVencimento;
    private LocalDate datePagamento;
}
