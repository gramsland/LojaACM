package service;

import model.Assinatura;

import java.math.BigDecimal;

public class CalculoValorAssinaturaPadrao implements CalculoValorAssinatura {
    @Override
    public BigDecimal calcularValorAssinatura(Assinatura assinatura) {
        BigDecimal mensalidade = assinatura.getMensalidade();
        long meses = assinatura.tempoEmMeses();

        return mensalidade.multiply(BigDecimal.valueOf(meses));
    }
}