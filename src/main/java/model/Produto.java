package model;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Produto {

    private String nome;
    private Path file;
    private BigDecimal preco;

    public String getNome() {
        return nome;
    }
    public BigDecimal getPreco() {
        return preco;
    }

    public Produto(String nome, Path file, BigDecimal preco) {
        this.nome = nome;
        this.file = file;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", file=" + file +
                ", preco=" + preco +
                '}';
    }
}
