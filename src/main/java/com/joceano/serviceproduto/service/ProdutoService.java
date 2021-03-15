package com.joceano.serviceproduto.service;

import com.joceano.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto save(Produto produto);

    Produto one(Long id);

    void delete(Long id);

    Produto update(Produto produto);
}
