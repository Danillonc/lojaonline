package br.com.lojaonline.commons.response;

import br.com.lojaonline.commons.enums.MessagesEnum;
import br.com.lojaonline.commons.message.Message;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    @JsonProperty(value = "messages")
    private List<String> messages;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private T data;

    public void addMessage(MessagesEnum message) {
        addMessage(message.getMessage());
        this.setStatusCode(message.getStatus().value());
        this.setTimestamp(LocalDateTime.now());
    }

    public void addMessage(String message) {
        if (Objects.isNull(messages)) {
            messages = new ArrayList<>();
        }
        this.messages.add(message);
    }

    public void addMessage(List<String> message) {
        if (Objects.isNull(messages)) {
            messages = new ArrayList<>();
        }
        this.messages.addAll(message);
    }
}
