package br.com.guiromao.meli.mercadolivre.infra.exception;

public class ProductNotExistsException extends RuntimeException {
    public ProductNotExistsException(String message) {
        super(message);
    }
}
