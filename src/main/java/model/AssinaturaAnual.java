package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaAnual extends Assinatura {

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, cliente);
    }

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, Cliente cliente) {
        super(mensalidade, dataInicio, cliente);
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
