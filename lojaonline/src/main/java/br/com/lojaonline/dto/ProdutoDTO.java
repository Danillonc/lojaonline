package br.com.lojaonline.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDTO {

    @NotEmpty(message = "codigoProduto não pode ser vazio.")
    @NotNull(message = "codigoProduto não pode ser nulo.")
    private String codigoProduto;

    @NotEmpty(message = "descricao não pode ser vazio.")
    @NotNull(message = "descricao não pode ser nulo.")
    private String descricao;

    @Positive(message = "estoque deve ser um valor positivo.")
    @NotNull(message = "estoque não pode ser nulo.")
    private Integer estoque;

    @NotNull(message = "preco não pode ser nulo.")
    @DecimalMin(value = "0", message = "preco não pode ser 0")
    @Digits(integer = 5, fraction = 2, message = "preco está no formato incorreto ex: 99.999,99")
    private BigDecimal preco;

    @NotEmpty(message = "Cor do produto não pode ser vazio.")
    @NotNull(message = "Cor do produto é obrigatório.")
    public String cor;

    @NotEmpty(message = "Fabricante não pode ser vazio.")
    @NotNull(message = "Fabricante é obrigatório.")
    public String fabricante;

    @NotEmpty(message = "Modelo não pode ser vazio.")
    @NotNull(message = "Modelo é obrigatoório.")
    public String modelo;

    @NotEmpty(message = "Serie não pode ser vazio.")
    @NotNull(message = "Serie é obrigatório.")
    public String serie;

}
