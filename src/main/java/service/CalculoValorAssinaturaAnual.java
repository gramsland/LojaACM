package service;

import model.Assinatura;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoValorAssinaturaAnual implements CalculoValorAssinatura {

    public static final BigDecimal TAXA_ASSINATURA = BigDecimal.ZERO;

    @Override
    public BigDecimal calcularValorAssinatura(Assinatura assinatura) {
        return assinatura.getMensalidade().multiply(BigDecimal.ONE.add(TAXA_ASSINATURA)).setScale(2, RoundingMode.HALF_UP);
    }
}
