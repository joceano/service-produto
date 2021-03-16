package com.joceano.serviceproduto.service;

import com.joceano.serviceproduto.event.ProdutoPersistEvent;
import com.joceano.serviceproduto.model.Produto;
import com.joceano.serviceproduto.repository.ProdutoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ApplicationEventPublisher publisher;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ApplicationEventPublisher publisher) {
        this.produtoRepository = produtoRepository;
        this.publisher = publisher;
    }

    @Override
    public Produto save(Produto produto) {
        Produto produtoPersist = produtoRepository.save(produto);
        publisher.publishEvent(new ProdutoPersistEvent(this, produto));
        return produtoPersist;
    }

    @Override
    public Produto one(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }

    @Override
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", id));
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto update(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", produto.getId()));
        }
        return save(produto);
    }
}
