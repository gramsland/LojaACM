package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaAnual extends Assinatura {

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento, cliente);
    }

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    @Override
    public BigDecimal CalcularValorAssinatura(){
        return this.getMensalidade().multiply(BigDecimal.valueOf(12));
    }

    @Override
    public BigDecimal CalcularTaxa() {
        return BigDecimal.ZERO;
    }
}
