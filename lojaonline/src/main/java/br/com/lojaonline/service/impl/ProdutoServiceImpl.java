package br.com.lojaonline.service.impl;

import br.com.lojaonline.commons.enums.MessagesEnum;
import br.com.lojaonline.commons.mapper.ProdutoMapper;
import br.com.lojaonline.commons.response.Response;
import br.com.lojaonline.domain.Produto;
import br.com.lojaonline.dto.ProdutoDTO;
import br.com.lojaonline.exception.ResourceAlreadyExistsException;
import br.com.lojaonline.exception.ResourceNotFoundException;
import br.com.lojaonline.repository.ProdutosRepository;
import br.com.lojaonline.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutosRepository repository;

    public ProdutoServiceImpl(ProdutosRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<Void> cadastrarProduto(Produto produto) {
        Response<Void> response = new Response<>();
        log.info("Inicio - ProdutoServiceImpl.cadastrarProduto {}", produto);

        this.repository.findByCodigoProduto(produto.getCodigoProduto()).ifPresent(
                p -> {
                    throw new ResourceAlreadyExistsException(MessagesEnum.PRODUTO_EXISTENTE.getMessage());
                }
        );

        this.repository.save(produto);
        response.addMessage(MessagesEnum.SUCESSO);
        log.info("Fim - ProdutoServiceImpl.cadastrarProduto {}", response);
        return response;
    }

    @Override
    public Response<List<ProdutoDTO>> consultarProdutos(String codigoProduto) {
        Response<List<ProdutoDTO>> response = new Response<>();

        log.info("Inicio - ProdutoServiceImpl.consultarProdutos {}", codigoProduto);

        if (Objects.nonNull(codigoProduto)) {
            List<Produto> produtos = new ArrayList<>();
            //TODO refatorar essa parte para uma maneira menos verbosa sem a necessidade da lista acima.
            this.repository.findByCodigoProduto(codigoProduto).ifPresentOrElse(
                    produto -> {
                        produtos.add(produto);
                        response.addMessage(MessagesEnum.SUCESSO);
                    },
                    () -> {
                        throw new ResourceNotFoundException(MessagesEnum.PRODUTO_NAO_ENCONTRADO.getMessage());
                    }
            );
            response.setData(produtos.stream().map(ProdutoMapper::toDto).collect(Collectors.toList()));
            return response;
        }
        response.setData(this.repository.findAll().stream().map(ProdutoMapper::toDto).collect(Collectors.toList()));
        response.addMessage(MessagesEnum.SUCESSO);
        log.info("Fim - ProdutoServiceImpl.consultarProdutos {}", response);
        return response;
    }

    @Override
    public Response<Void> atualizarProduto(Produto produto, String codigoProduto) {
        Response<Void> response = new Response<>();
        log.info("Inicio - ProdutoServiceImpl.atualizarProduto codigo: {}, produto: {}", codigoProduto, produto);

        this.repository.findByCodigoProduto(codigoProduto).ifPresentOrElse(
                produtoDb -> {
                    produtoDb.setCor(produto.getCor());
                    produtoDb.setFabricante(produto.getFabricante());
                    produtoDb.setSerie(produto.getSerie());
                    produtoDb.setModelo(produto.getModelo());
                    produtoDb.setDescricao(produto.getDescricao());
                    produtoDb.setPreco(produto.getPreco());
                    produtoDb.setEstoque(produto.getEstoque());
                    this.repository.save(produtoDb);
                    response.addMessage(MessagesEnum.SUCESSO);
                },
                () -> {
                    throw new ResourceNotFoundException(MessagesEnum.PRODUTO_NAO_ENCONTRADO.getMessage());
                }
        );
        log.info("Fim - ProdutoServiceImpl.atualizarProduto {}", response);
        return response;
    }

    @Override
    public Response<Void> deletarProduto(String codigoProduto) {
        Response<Void> response = new Response<>();
        log.info("Inicio - ProdutoServiceImpl.deletarProduto codigo: {}", codigoProduto);

        this.repository.findByCodigoProduto(codigoProduto).ifPresentOrElse(
                produto -> {
                    this.repository.deleteById(produto.getId());
                    response.addMessage(MessagesEnum.SUCESSO);
                },
                () -> {
                    throw new ResourceNotFoundException(MessagesEnum.PRODUTO_NAO_ENCONTRADO.getMessage());
                }
        );
        log.info("Fim - ProdutoServiceImpl.deletarProduto {}", response);
        return response;
    }


}
