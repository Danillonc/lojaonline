package br.com.lojaonline.service;


import br.com.lojaonline.commons.response.Response;
import br.com.lojaonline.domain.Produto;
import br.com.lojaonline.dto.ProdutoDTO;

import java.util.List;

/**
 * Interface responsável por definir os contratos da camada de service de produto.
 */
public interface ProdutoService {

    /**
     * Metodo responsavel por cadastrar um produto.
     * @param produto Objeto com os dados do produto.
     * @return Response<Void>
     */
    Response<Void> cadastrarProduto(final Produto produto);

    /**
     * Método responsável por realizar a busca do produto informado ou todos os produtos.
     * @param codigoProduto - Codigo do produto para consulta.
     * @return Response<List<ProdutoDTO>>
     */
    Response<List<ProdutoDTO>> consultarProdutos(final String codigoProduto);

    /**
     * Método responsável por atualizar um produto na base de dados.
     * @param produto - Objeto com os dados do produto.
     * @param codigoProduto - Código identificador do produto.
     * @return
     */
    Response<Void> atualizarProduto(final Produto produto, final String codigoProduto);

    Response<Void> deletarProduto(final String codigoProduto);
}
