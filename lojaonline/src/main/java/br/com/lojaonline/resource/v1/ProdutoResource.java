package br.com.lojaonline.resource.v1;

import br.com.lojaonline.commons.mapper.ProdutoMapper;
import br.com.lojaonline.commons.response.Response;
import br.com.lojaonline.dto.ProdutoDTO;
import br.com.lojaonline.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    private ProdutoService service;

    ProdutoResource(ProdutoService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<Response<Void>> criarProduto(@RequestBody @Valid ProdutoDTO produtoDto) {
        Response<Void> response = this.service.cadastrarProduto(ProdutoMapper.toEntity(produtoDto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultar")
    public ResponseEntity<Response<List<ProdutoDTO>>> consultarProdutos(@RequestParam(required = false) final String codigoProduto) {
        Response<List<ProdutoDTO>> response = this.service.consultarProdutos(codigoProduto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/atualizar/{codigoProduto}")
    public ResponseEntity<Response<Void>> atualizarProduto(@RequestBody @Valid ProdutoDTO produtoDto, @PathVariable("codigoProduto") String codigoProduto) {
        Response<Void> response = this.service.atualizarProduto(ProdutoMapper.toEntity(produtoDto), codigoProduto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletar/{codigoProduto}")
    public ResponseEntity<Response<Void>> deletarProduto(@PathVariable("codigoProduto") String codigoProduto) {
        Response<Void> response = this.service.deletarProduto(codigoProduto);
        return ResponseEntity.ok(response);
    }

}
