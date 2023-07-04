package model;

import service.CalculoValorAssinatura;
import service.CalculoValorAssinaturaSemestral;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaSemestral extends Assinatura {

    public static final BigDecimal MESES = BigDecimal.valueOf(6);

    private CalculoValorAssinatura calculoValorAssinatura = new CalculoValorAssinaturaSemestral();

    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento,Cliente cliente) {
        super(mensalidade, dataInicio, dataFim, dataVencimento, dataPagamento,cliente);
    }

    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento,Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    public BigDecimal calcularValorAssinatura() {
        return calculoValorAssinatura.calcularValorAssinatura(this);
    }

}
