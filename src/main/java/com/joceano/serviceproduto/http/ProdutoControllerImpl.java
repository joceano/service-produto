package com.joceano.serviceproduto.http;

import com.joceano.serviceproduto.http.data.request.ProdutoPersistDto;
import com.joceano.serviceproduto.model.Produto;
import com.joceano.serviceproduto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("produto")
public class ProdutoControllerImpl  implements ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@Valid @RequestBody ProdutoPersistDto dto) {
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        return produtoService.inserir(produto);
    }

    @Override
    @GetMapping("{id}")
    public Produto one(@PathVariable("id") Long id) {
        return  produtoService.one(id);
    }
}
