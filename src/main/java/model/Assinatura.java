package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Assinatura {

    private BigDecimal mensalidade;
    private LocalDateTime dataInicio;
    private Optional<LocalDateTime> dataFim;
    private Cliente cliente;


    public Assinatura(BigDecimal mensalidade, LocalDateTime dataInicio, LocalDateTime dataFim, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.dataInicio = dataInicio;
        this.dataFim = Optional.of(dataFim);
        this.cliente = cliente;
    }

    public Assinatura(BigDecimal mensalidade, LocalDateTime dataInicio, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.dataInicio = dataInicio;
        this.dataFim = Optional.empty();
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



}
