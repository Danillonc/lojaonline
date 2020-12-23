package br.com.lojaonline.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ProdutoBase {

    @Id
    @GeneratedValue
    private Long id;

    private String codigoProduto;

    private String descricao;

    private Integer estoque;

    private BigDecimal preco;
}
