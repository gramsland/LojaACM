package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaSemestral extends Assinatura {

    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento,Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento,cliente);
    }

    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento,Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    @Override
    public BigDecimal CalcularValorAssinatura() {
        return this.getMensalidade().multiply(BigDecimal.valueOf(6));
    }

    @Override
    public BigDecimal CalcularTaxa() {
       return CalcularValorAssinatura().multiply(BigDecimal.valueOf(0.03));
    }

}
