package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Assinatura {

    private BigDecimal mensalidade;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private Cliente cliente;


    public Assinatura(BigDecimal mensalidade, LocalDate dataInicio, LocalDate dataFim, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
    }

    public Assinatura(BigDecimal mensalidade, LocalDate dataInicio, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.dataInicio = dataInicio;
        this.cliente = cliente;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean ativa() {
        return dataFim == null;
    }

    public long tempoEmMesesAtiva() {
        if (ativa()) {
            return dataInicio.until(LocalDate.now()).toTotalMonths();
        }
        return dataInicio.until(dataFim).toTotalMonths();
    }

    public double valorPago() {
        return mensalidade.doubleValue() * tempoEmMesesAtiva();
    }

}
