package com.joceano.serviceproduto.http.data.response;

public class ProdutoResponseDto {

    private final Long id;
    private final String descricao;

    public ProdutoResponseDto(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
