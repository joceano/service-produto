package com.joceano.serviceproduto.http.data.request;

import java.math.BigDecimal;

public class ProdutoPersistDto {

    private String descricao;

    private BigDecimal valor;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
