package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Pagamento {

    private List<Produto> produtos;
    private LocalDate dataCompra;
    private Cliente cliente;
    public List<Produto> getProdutos() {
        return produtos;
    }
    public LocalDate getDataCompra() {
        return dataCompra;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public Pagamento(List<Produto> produtos, LocalDate dataCompra, Cliente cliente) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public BigDecimal somarPagamentos(){

        return getProdutos()
                .stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "produtos=" + produtos +
                ", dataCompra=" + dataCompra +
                ", cliente=" + cliente +
                '}';
    }
}
