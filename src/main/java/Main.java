import model.*;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import exception.ClienteNaoEncontradoException;

public class Main {

    public static void main(String[] args) {

        Produto produtoUm = new Produto("Produto 1", Paths.get("/produtos/fear-of-the-dark.mp3") , new BigDecimal("10.00"));
        Produto produtoDois = new Produto("Produto 2", Paths.get("/produtos/Avatar.mp4") , new BigDecimal("20.00"));
        Produto produtoTres = new Produto("Produto 3", Paths.get("/produtos/monalisa.jpeg") , new BigDecimal("30.00"));

        Cliente clienteUm = new Cliente("João");
        Cliente clienteDois = new Cliente("Maria");
        Cliente clienteTres = new Cliente("José");

        Pagamento pagamentoUm = new Pagamento(List.of(produtoUm, produtoDois), LocalDate.now(), clienteUm);
        Pagamento pagamentoDois = new Pagamento(List.of(produtoDois, produtoTres), LocalDate.now().minusDays(1), clienteDois);
        Pagamento pagamentoTres = new Pagamento(List.of(produtoTres, produtoUm), LocalDate.now().minusMonths(1), clienteTres);

        System.out.println("==============================================");
        System.out.println("2 - Ordene e imprima os pagamentos pela data de compra.");
        List<Pagamento> listaDePagamentos = List.of(pagamentoUm, pagamentoDois, pagamentoTres);

        List<Pagamento> listaDePagamentosOrdenada = listaDePagamentos
                .stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra))
                .collect(Collectors.toList());

        listaDePagamentosOrdenada.forEach(p -> System.out.println(p.getDataCompra()));

        System.out.println("==============================================");
        System.out.println("3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.");

        Optional<BigDecimal> somaOptional = pagamentoUm.getProdutos().stream()
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
        System.out.println("7 - Qual cliente gastou mais?");

        Map<Cliente, BigDecimal> gastoPorCliente = listaDePagamentos
                .stream()
                .collect(Collectors.groupingBy(Pagamento::getCliente, Collectors.reducing(BigDecimal.ZERO, Pagamento::somarPagamentos, BigDecimal::add))
        );

        Optional<Cliente> quemGastouMais = gastoPorCliente.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        System.out.println("Cliente que gastou mais: " + quemGastouMais.map(Cliente::getNome).orElseThrow(ClienteNaoEncontradoException::new));

        System.out.println("==============================================");
        System.out.println("8 - Quanto foi faturado em um determinado mês?");

        int mes = 6;

        BigDecimal faturamentoMes = listaDePagamentos
                .stream()
                .filter(pagamento -> pagamento.getDataCompra().getMonthValue() == mes)
                .map(Pagamento::somarPagamentos)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Faturamento do mês " + mes + ": " + faturamentoMes);

        System.out.println("==============================================");
        System.out.println("9 - Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas.");

        Assinatura assintaturaUm = new AssinaturaTrimestral(new BigDecimal("99.98"), LocalDateTime.now(), LocalDateTime.of(2023, 7, 1, 0, 0), LocalDateTime.of(2023, 7, 1, 0, 0), clienteUm);
        Assinatura assinaturaDois = new AssinaturaSemestral(new BigDecimal("99.98"), LocalDateTime.now().minusMonths(2), LocalDateTime.now(), LocalDateTime.of(2023, 7, 1, 0, 0), LocalDateTime.of(2023, 7, 2, 0, 0), clienteDois);
        Assinatura assinaturaTres = new AssinaturaAnual(new BigDecimal("99.98"), LocalDateTime.now().minusMonths(1), LocalDateTime.now(), LocalDateTime.of(2023, 7, 1, 0, 0), LocalDateTime.of(2023, 7, 2, 0, 0), clienteTres);

        System.out.println("Assinatura :" + assintaturaUm.getMensalidade() + ": " + assintaturaUm.getDataInicio() + ": " + assintaturaUm.getCliente().getNome());
        System.out.println("Assinatura :" + assinaturaDois.getMensalidade() + ": " + assinaturaDois.getDataInicio() + ": " + assinaturaDois.getDataFim() + ": " + assinaturaDois.getCliente().getNome());
        System.out.println("Assinatura :" + assinaturaTres.getMensalidade() + ": " + assinaturaTres.getDataInicio() + ": " + assinaturaTres.getDataFim() + ": " + assinaturaTres.getCliente().getNome());

        System.out.println("==============================================");
        System.out.println("10 - Imprima o tempo em meses de alguma assinatura ainda ativa.");

        System.out.println("Tempo de assinaturaDois ativa: " + ChronoUnit.MONTHS.between(assinaturaDois.getDataInicio(), assinaturaDois.getDataFim().orElse(LocalDateTime.now()))+ " meses");
        System.out.println("==============================================");
        System.out.println("11 - Imprima o tempo de meses entre o start e end de todas assinaturas. Não utilize IFs para assinaturas sem end Time.");

        List<Assinatura> assinaturas = List.of(assintaturaUm, assinaturaDois, assinaturaTres);

        assinaturas.forEach(assinaturaPresente -> {
            long mesesEntreInicioEFim  = ChronoUnit.MONTHS.between(assinaturaPresente.getDataInicio(), assinaturaPresente.getDataFim().orElse(LocalDateTime.now()));

            System.out.println("Tempo em meses da assinatura: " + mesesEntreInicioEFim);
        });

        System.out.println("==============================================");
        System.out.println("12 - Calcule o valor pago em cada assinatura até o momento.");

        assinaturas.forEach(assinaturaExistente -> {
            long mesesDecorridos = assinaturaExistente.getDataInicio().until(LocalDateTime.now(), ChronoUnit.MONTHS);
            BigDecimal valorPago = assinaturaExistente.getMensalidade().multiply(BigDecimal.valueOf(mesesDecorridos));
            System.out.println("Valor pago na assinatura até o momento: " + valorPago);
        });

        assintaturaUm.realizarCompra();
        assinaturaDois.realizarCompra();
        assinaturaTres.realizarCompra();

    }
}
