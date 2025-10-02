package br.com.guiromao.meli.mercadolivre.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotExistsException.class)
    public ErrorDTO handleProductNotExists(ProductNotExistsException ex) {
        return new ErrorDTO(ex.getMessage());
    }
}
