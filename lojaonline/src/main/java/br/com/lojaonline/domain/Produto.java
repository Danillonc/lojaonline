package br.com.lojaonline.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuperBuilder
@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("Produto")
public class Produto extends ProdutoBase {

    public String cor;
    public String fabricante;
    public String modelo;
    public String serie;

}
