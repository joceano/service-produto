package com.joceano.serviceproduto.http;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
        return produtoService.save(produto);
    }

    @Override
    @GetMapping("{id}")
    public Produto one(@PathVariable("id") Long id) {
        return  produtoService.one(id);
    }

    @Override
    @PatchMapping("{id}")
    public Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Produto produto = produtoService.one(id);

        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
        
        JsonNode produtoJsonNode = objectMapper.convertValue(produto, JsonNode.class);
        JsonNode patchJsonNode = patch.apply(produtoJsonNode);
        Produto produtoPersist = objectMapper.treeToValue(patchJsonNode, Produto.class);
        return produtoService.save(produtoPersist);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
    }

    @PutMapping("{id}")
    public Produto updateAll(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto) {
        Produto produto = new Produto(id, dto.getDescricao(), dto.getValor());
        return produtoService.update(produto);
    }
}
