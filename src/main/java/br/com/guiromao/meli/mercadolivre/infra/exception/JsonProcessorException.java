package br.com.guiromao.meli.mercadolivre.infra.exception;

public class JsonProcessorException extends RuntimeException {
    public JsonProcessorException(String message) {
        super(message);
    }
}
