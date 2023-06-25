import model.Assinatura;
import model.Cliente;
import model.Pagamento;
import model.Produto;
import service.FaturamentoCalculator;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {

    public static void main(String[] args) {

        Produto produto1 = new Produto("Produto 1", Paths.get("/produtos/fear-of-the-dark.mp3") , new BigDecimal("10.00"));
        Produto produto2 = new Produto("Produto 2", Paths.get("/produtos/Avatar.mp4") , new BigDecimal("20.00"));
        Produto produto3 = new Produto("Produto 3", Paths.get("/produtos/monalisa.jpeg") , new BigDecimal("30.00"));

        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");
        Cliente cliente3 = new Cliente("José");

        Assinatura assinatura1 = new Assinatura(new BigDecimal("10.00"), LocalDate.now(), cliente1);
        Assinatura assinatura2 = new Assinatura(new BigDecimal("20.00"), LocalDate.now(), cliente2);
        Assinatura assinatura3 = new Assinatura(new BigDecimal("30.00"), LocalDate.now(), cliente3);

        Pagamento pagamento1 = new Pagamento(List.of(produto1, produto2), LocalDate.now(), cliente1);
        Pagamento pagamento2 = new Pagamento(List.of(produto2, produto3), LocalDate.now().minusDays(1), cliente2);
        Pagamento pagamento3 = new Pagamento(List.of(produto3, produto1), LocalDate.now().minusMonths(1), cliente3);

        System.out.println("==============================================");
        System.out.println("2 - Ordene e imprima os pagamentos pela data de compra.");
        List<Pagamento> listaDePagamentos = List.of(pagamento1, pagamento2, pagamento3);

        List<Pagamento> listaDePagamentosOrdenada = listaDePagamentos
                .stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra))
                .collect(Collectors.toList());

        listaDePagamentosOrdenada.forEach(p -> System.out.println(p.getDataCompra()));

        System.out.println("==============================================");
        System.out.println("3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.");

        Optional<BigDecimal> somaOptional = pagamento1.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal::add);

        BigDecimal soma = somaOptional.orElse(BigDecimal.ZERO);

        System.out.println("Soma dos valores: " + soma);

        System.out.println("==============================================");
        System.out.println("4 -  Calcule o Valor de todos os pagamentos da Lista de pagamentos.");
        listaDePagamentos
                .stream()
                .map(Pagamento::somarPagamentos)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("==============================================");
        System.out.println("5 - Imprima a quantidade de cada Produto vendido.");
        listaDePagamentos.stream()
                .flatMap(p -> p.getProdutos().stream())
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()))
                .forEach((p, qtd) -> System.out.println(p.getNome() + " - " + qtd));

        System.out.println("==============================================");
        System.out.println("6 - Crie um Mapa de <Cliente, List<Produto>");
        Map<Cliente, List<Produto>> mapaClientesProdutos = listaDePagamentos.stream()
                .collect(
                        Collectors.groupingBy(
                                Pagamento::getCliente,
                                Collectors.flatMapping(pagamento -> pagamento.getProdutos().stream(), Collectors.toList())
                        )
                );
        mapaClientesProdutos.forEach((cliente, produtos) -> System.out.println(cliente.getNome() + " - " + produtos.toString()));

        System.out.println("==============================================");
        System.out.println("8 - Quanto foi faturado em um determinado mês?");

        FaturamentoCalculator faturamentoCalculator = new FaturamentoCalculator();
        int mes = LocalDateTime.now().getMonthValue();
        int ano = LocalDateTime.now().getYear();
        BigDecimal faturamento = faturamentoCalculator.calcularFaturamentoPorMes(listaDePagamentos, mes, ano);
        System.out.println("Faturamento do mês " + mes + "/" + ano + ": " + faturamento);

        System.out.println("==============================================");
        System.out.println("9 - Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas.");

        Assinatura assinaturaAtiva = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(1), cliente1);
        Assinatura assinaturaEncerrada1 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(2), LocalDate.now(), cliente2);
        Assinatura assinaturaEncerrada2 = new Assinatura(new BigDecimal("99.98"), LocalDate.now().minusMonths(3), LocalDate.now().minusDays(15), cliente3);

        System.out.println("==============================================");
        System.out.println("10 - Imprima o tempo de meses ativa de todas assinaturas. Não utilize IFs para assinaturas sem end Time.");
        System.out.println("Tempo em meses ativa: " + assinaturaAtiva.tempoEmMesesAtiva());

        System.out.println("==============================================");
        System.out.println("11 - Imprima o tempo de meses ativa de todas assinaturas. Não utilize IFs para assinaturas sem end Time.");
        List<Assinatura> listaDeAssinaturas = List.of(assinaturaAtiva, assinaturaEncerrada1, assinaturaEncerrada2, assinatura1, assinatura2, assinatura3);
        listaDeAssinaturas.stream()
                .mapToLong(Assinatura::tempoEmMesesAtiva)
                .forEach(
                        tempoEmMeses -> System.out.println("Tempo em meses: " + tempoEmMeses)
                );

        System.out.println("==============================================");
        System.out.println("12 - Calcule o valor pago em cada assinatura até o momento.");
        DoubleStream valorPagoPorAssinatura= listaDeAssinaturas.stream()
                .mapToDouble(Assinatura::valorPago);

        valorPagoPorAssinatura.forEach(System.out::println);
    }
}
