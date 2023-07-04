package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaSemestral extends Assinatura {

    public static final BigDecimal MESES = BigDecimal.valueOf(6);

    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento,Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento,cliente);
    }

    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento,Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    @Override
    public BigDecimal calcularValorAssinatura() {
        return this.getMensalidade().multiply(BigDecimal.ONE.add(getTaxa()));
    }

    @Override
    public BigDecimal getTaxa() {
        return BigDecimal.valueOf(0.03);
    }

}
