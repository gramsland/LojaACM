package model;

import service.CalculoValorAssinatura;
import service.CalculoValorAssinaturaAnual;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaAnual extends Assinatura {

    public static final BigDecimal MESES = BigDecimal.valueOf(12);
    private CalculoValorAssinatura calculoValorAssinatura = new CalculoValorAssinaturaAnual();

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento, cliente);
    }

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    public BigDecimal calcularValorAssinatura() {
        return calculoValorAssinatura.calcularValorAssinatura(this);
    }

    @Override
    public BigDecimal valorPago() {
        return this.calcularValorAssinatura().multiply(BigDecimal.valueOf(this.tempoEmMeses()));
    }
}
