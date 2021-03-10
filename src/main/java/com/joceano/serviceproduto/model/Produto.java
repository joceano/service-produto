package com.joceano.serviceproduto.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Deprecated
    public Produto() {
    }

    public Produto(@NonNull String descricao, @NonNull BigDecimal valor) {
        this.descricao = Objects.requireNonNull(descricao);
        this.valor = Objects.requireNonNull(valor);
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
