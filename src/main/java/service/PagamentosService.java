package service;

import model.Cliente;
import model.Pagamento;
import model.Produto;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PagamentosService {

    public static List<Pagamento> getPagamentosOrdenados(List<Pagamento> pagamentos) {

        List<Pagamento> listaDePagamentosOrdenada = pagamentos
                .stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra))
                .collect(Collectors.toList());
        ;
        return listaDePagamentosOrdenada;
    }

    public static BigDecimal calcularSomasPagamento(Pagamento pagamento) {
        Optional<BigDecimal> somaOptional = pagamento.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal::add);

        BigDecimal soma = somaOptional.orElse(BigDecimal.ZERO);

        return soma;
    }

    public static List<BigDecimal> calcularSomasPagamentos(List<Pagamento> listaDePagamentos) {
        return listaDePagamentos
                .stream()
                .map(Pagamento::somarPagamentos)
                .collect(Collectors.toList());
    }


    public static java.util.Map<Produto, Long> CalcularQuantidadeProdutosVendidos(List<Pagamento> listaDePagamentos) {
        return listaDePagamentos.stream()
                .flatMap(p -> p.getProdutos().stream())
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    public static Map<Cliente, List<Produto>> listarProdutosPorCliente(List<Pagamento> listaDePagamentos) {
        Map<Cliente, List<Produto>> mapaClientesProdutos = listaDePagamentos.stream()
                .collect(
                        Collectors.groupingBy(
                                Pagamento::getCliente,
                                Collectors.flatMapping(pagamento -> pagamento.getProdutos().stream(), Collectors.toList())
                        )
                );
        return mapaClientesProdutos;
    }


    public static Map<Cliente, BigDecimal> listarGastoPorCliente(List<Pagamento> listaDePagamentos) {

        return listaDePagamentos
                .stream()
                .collect(Collectors.groupingBy(Pagamento::getCliente, Collectors.reducing(BigDecimal.ZERO, Pagamento::somarPagamentos, BigDecimal::add))
                );
    }

    public static Optional<Cliente> listarClienteQueGastouMais(List<Pagamento> listaDePagamentos) {

        Map<Cliente, BigDecimal> gastoPorCliente = listarGastoPorCliente(listaDePagamentos);

        Optional<Cliente> quemGastouMais = gastoPorCliente.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        return quemGastouMais;
    }

    public static BigDecimal listarFaturamentoMes(List<Pagamento> listaDePagamentos, int mes) {
        BigDecimal faturamentoMes = listaDePagamentos
                .stream()
                .filter(pagamento -> pagamento.getDataCompra().getMonthValue() == mes)
                .map(Pagamento::somarPagamentos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return faturamentoMes;
    }

}
