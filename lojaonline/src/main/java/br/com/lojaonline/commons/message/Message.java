package br.com.lojaonline.commons.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {

    private String code;
    private String msg;

}
