package br.com.guiromao.meli.mercadolivre.infra.exception;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GlobalExceptionHandlerTest {

    @Test
    void handleProductNotExistsAndReturnsErrorDTOWithCorrectMessage() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String errorMessage = "Produto não encontrado";
        ProductNotExistsException exception = new ProductNotExistsException(errorMessage);

        ErrorDTO errorDTO = handler.handleProductNotExists(exception);

        assertThat(errorDTO).isNotNull();
        assertThat(errorDTO.message()).isEqualTo(errorMessage);
    }
}
