package br.com.lojaonline.config;

import br.com.lojaonline.commons.response.ExceptionResponse;
import br.com.lojaonline.commons.response.Response;
import br.com.lojaonline.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe responsável por definir algumas exceçoes
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> handlerGlobalExceptions(Exception ex){
        List<String> erros = new ArrayList<>();
        erros.add(ex.getLocalizedMessage());
        Response<Void> exceptionResponse = new Response<>();
        exceptionResponse.addMessage(erros);
        exceptionResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<Response>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Response> handlerResourceNotFound(Exception ex, WebRequest request){
        List<String> erros = new ArrayList<>();
        erros.add(ex.getLocalizedMessage());
        Response<Void> exceptionResponse = new Response<>();
        exceptionResponse.addMessage(erros);
        exceptionResponse.setStatusCode(HttpStatus.FOUND.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<Response>(exceptionResponse, HttpStatus.FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            erros.add(error.getDefaultMessage());
        }
        Response<Void> exceptionResponse = new Response<>();
        exceptionResponse.addMessage(erros);
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
