package br.com.lojaonline.commons.mapper;

import br.com.lojaonline.domain.Produto;
import br.com.lojaonline.dto.ProdutoDTO;

/**
 * Classe responsável por realizar a conversao entre objetos.
 */
public class ProdutoMapper {

    public static Produto toEntity(final ProdutoDTO dto){
        return Produto.builder().codigoProduto(dto.getCodigoProduto()).descricao(dto.getDescricao())
                .estoque(dto.getEstoque()).preco(dto.getPreco()).cor(dto.getCor())
                .fabricante(dto.getFabricante()).modelo(dto.getModelo()).serie(dto.getSerie()).build();
    }

    public static ProdutoDTO toDto(final Produto entity){
        return ProdutoDTO.builder().codigoProduto(entity.getCodigoProduto()).descricao(entity.getDescricao())
                .estoque(entity.getEstoque()).preco(entity.getPreco()).cor(entity.getCor())
                .fabricante(entity.getFabricante()).modelo(entity.getModelo()).serie(entity.getSerie()).build();
    }
}
