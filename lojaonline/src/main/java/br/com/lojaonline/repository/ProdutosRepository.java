package br.com.lojaonline.repository;

import br.com.lojaonline.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface que definie os contratos da camada de repository de produto.
 */
@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodigoProduto(final String codigo);
}
