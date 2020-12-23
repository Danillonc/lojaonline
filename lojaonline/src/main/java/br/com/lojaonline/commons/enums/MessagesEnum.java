package br.com.lojaonline.commons.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Enum responsável por defiir as mensagens de retorno das APIs
 */
@Getter
public enum MessagesEnum {

    SUCESSO(HttpStatus.OK, "OK."),
    PRODUTO_CRIADO(HttpStatus.CREATED, "Produto criado com sucesso."),
    PRODUTO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Produto não encontrado."),
    PRODUTO_EXISTENTE(HttpStatus.FOUND, "Produto existente."),
    CLIENTE_SEM_SALDO(HttpStatus.PRECONDITION_FAILED, "Saldo insuficiente.");

    private HttpStatus status;
    private String message;

    MessagesEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
