package service;

import model.Pagamento;
import model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FaturamentoCalculator {

    public BigDecimal calcularFaturamentoPorMes(List<Pagamento> pagamentos, int mes, int ano) {
        LocalDate dataInicio = LocalDate.of(ano, mes, 1);
        LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());

        return pagamentos.stream()
                .filter(p -> dentroDoMes(p.getDataCompra(), dataInicio, dataFim))
                .flatMap(p -> p.getProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean dentroDoMes(LocalDate data, LocalDate dataInicio, LocalDate dataFim) {
        return !data.isBefore(dataInicio) && !data.isAfter(dataFim);
    }
}
