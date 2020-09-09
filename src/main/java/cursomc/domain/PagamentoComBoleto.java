package cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import cursomc.domain.enums.EstadoPagamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PagamentoComBoleto extends Pagamento {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datePagamento;

    public PagamentoComBoleto(){}

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, LocalDate dateVencimento, LocalDate datePagamento) {
        super(id, estado, pedido);
        this.dateVencimento = dateVencimento;
        this.datePagamento = datePagamento;
    }
}
