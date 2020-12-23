package br.com.lojaonline.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO {
    private String codigoPedido;
    private String dataCompra;
    private String nomeComprador;
    private String status;
    private BigDecimal frete;
    
}
