package service;

import model.Assinatura;

import java.math.BigDecimal;

public interface CalculoValorAssinatura {
    BigDecimal calcularValorAssinatura(Assinatura assinatura);

}

