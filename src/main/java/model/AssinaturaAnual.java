package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaAnual extends Assinatura {

    public static final BigDecimal MESES = BigDecimal.valueOf(12);

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento, cliente);
    }

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    @Override
    public BigDecimal calcularValorAssinatura(){
        return this.getMensalidade().multiply((BigDecimal.ONE.add(getTaxa())));
    }

    @Override
    public BigDecimal getTaxa() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal valorPago() {
        return this.calcularValorAssinatura().multiply(BigDecimal.valueOf(this.tempoEmMeses()));
    }
}
