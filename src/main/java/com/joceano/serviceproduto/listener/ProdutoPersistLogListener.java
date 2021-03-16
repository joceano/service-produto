package com.joceano.serviceproduto.listener;

import com.joceano.serviceproduto.event.ProdutoPersistEvent;
import com.joceano.serviceproduto.model.Produto;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProdutoPersistLogListener implements ApplicationListener<ProdutoPersistEvent> {

    @Override
    public void onApplicationEvent(ProdutoPersistEvent event) {
        Produto produto = event.getProduto();
        System.out.println(produto.getDescricao());
    }
}
