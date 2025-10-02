package br.com.guiromao.meli.mercadolivre.repository;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductRepositoryTest {

    @Test
    void throwsJsonProcessorException_WhenJsonFileIsInvalid() {
        ProductRepository repository = new ProductRepository();
        // Simula um caminho inválido para forçar IOException
        String invalidPath = "caminho/inexistente.json";
        assertThatThrownBy(() -> {
            // Usa reflection para acessar o método privado
            var method = ProductRepository.class.getDeclaredMethod("products", String.class);
            method.setAccessible(true);
            method.invoke(repository, invalidPath);
        }).hasCauseInstanceOf(br.com.guiromao.meli.mercadolivre.infra.exception.JsonProcessorException.class)
                .hasRootCauseMessage("Erro ao ler ou processar o arquivo JSON");
    }
}
