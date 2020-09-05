package cursomc.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoComBoleto extends Pagamento {

    private LocalDate dateVencimento;
    private LocalDate datePagamento;
}
