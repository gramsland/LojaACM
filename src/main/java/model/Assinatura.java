package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public abstract class Assinatura {

    private BigDecimal mensalidade;
    private LocalDateTime dataInicio;

    private Optional<LocalDateTime>  dataPagamento;
    private LocalDateTime dataVencimento;
    private Optional<LocalDateTime> dataFim;
    private Cliente cliente;
    private boolean atrasoPagamento;


    public Assinatura(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.dataInicio = dataInicio;
        this.dataFim = Optional.of(dataFim);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = Optional.of(dataPagamento);
        this.cliente = cliente;
    }

    public Assinatura(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataVencimento, LocalDateTime dataPagamento, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.dataInicio = dataInicio;
        this.dataFim = Optional.empty();
        this.dataVencimento = dataVencimento;
        this.dataPagamento = Optional.of(dataPagamento);
        this.cliente = cliente;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Optional<LocalDateTime> getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = Optional.of(dataFim);
    }

    public Optional<LocalDateTime> getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Optional<LocalDateTime> dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setAtrasoPagamento(boolean atrasoPagamento) {
        this.atrasoPagamento = atrasoPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public abstract BigDecimal CalcularValorAssinatura();
    public abstract BigDecimal CalcularTaxa();

    public void realizarCompra() {
        boolean atraso = isAtrasoPagamento();
        if (atraso) {
            System.out.println("Não é possível realizar a compra. A assinatura está em atraso de pagamento.");
        } else {
            // Lógica para realizar a compra
            System.out.println("Compra realizada com sucesso.");
        }
    }

    public boolean isAtrasoPagamento() {
        return dataPagamento.map(pagamento -> pagamento.isAfter(dataVencimento)).orElse(false);
    }
}
