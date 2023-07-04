package model;

import service.CalculoValorAssinatura;
import service.CalculoValorAssinaturaTrimestral;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaTrimestral extends Assinatura{

    public static final BigDecimal MESES = BigDecimal.valueOf(3);
    private CalculoValorAssinatura calculoValorAssinatura = new CalculoValorAssinaturaTrimestral();

    public AssinaturaTrimestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento, cliente);
    }

    public AssinaturaTrimestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    public BigDecimal calcularValorAssinatura() {
        return calculoValorAssinatura.calcularValorAssinatura(this);
    }

}
