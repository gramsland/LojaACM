package model;

import service.CalculoValorAssinatura;
import service.CalculoValorAssinaturaTrimestral;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssinaturaTrimestral extends Assinatura{

    private CalculoValorAssinatura calculoValorAssinatura = new CalculoValorAssinaturaTrimestral();

    public AssinaturaTrimestral(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        super(mensalidade, dataInicio, dataVencimento, dataPagamento, cliente);
    }

    public BigDecimal calcularValorAssinatura() {
        return calculoValorAssinatura.calcularValorAssinatura(this);
    }

}
