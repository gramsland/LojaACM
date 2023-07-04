package model;

import service.CalculoValorAssinatura;
import service.CalculoValorAssinaturaPadrao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public abstract class Assinatura {

    private BigDecimal mensalidade;
    private LocalDateTime dataInicio;
    private Optional<LocalDateTime>  dataPagamento;
    private LocalDateTime dataVencimento;
    private Optional<LocalDateTime> dataFim;
    private Cliente cliente;

    private CalculoValorAssinatura calculoValorAssinatura = new CalculoValorAssinaturaPadrao();

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

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public Optional<LocalDateTime> getDataFim() {
        return dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal calcularValorAssinatura() {
        return calculoValorAssinatura.calcularValorAssinatura(this);
    }

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

    public long tempoEmMeses(){
        return ChronoUnit.MONTHS.between(this.getDataInicio(), this.getDataFim().orElse(LocalDateTime.now()));
    }

    public BigDecimal valorPago(){
        return this.calcularValorAssinatura().multiply(BigDecimal.valueOf(this.tempoEmMeses()));
    }
}
