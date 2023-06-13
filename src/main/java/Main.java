import model.Assinatura;
import model.Cliente;
import model.Pagamento;
import model.Produto;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //1 Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos.
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

        //2 - Ordene e imprima os pagamentos pela data de compra.
        List<Pagamento> listaDePagamentos = List.of(pagamento1, pagamento2, pagamento3);

        List<Pagamento> listaDePagamentosOrdenada = listaDePagamentos
                .stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra))
                .collect(Collectors.toList());

        listaDePagamentos.forEach(p -> System.out.println(p.getDataCompra()));

        //3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.


    }
}
