package com.joceano.serviceproduto.http.data.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoPersistDto {

    @NotEmpty
    private String descricao;

    @NotNull
    private BigDecimal valor;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
