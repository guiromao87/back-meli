package br.com.guiromao.meli.mercadolivre.util;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Product> loadProducts() {
        try (InputStream inputStream = getProductStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (JsonParseException | JsonMappingException e) {
            throw new RuntimeException("Invalid JSON format in products.json: " + e.getMessage(), e);
        } catch (IOException e) {
            // Erros de leitura geral também são tratados da mesma forma para unificar tratamento
            throw new RuntimeException("Error reading products.json: " + e.getMessage(), e);
        }
    }

    static InputStream getProductStream() {
        return ProductLoader.class.getResourceAsStream("/products.json");
    }
}
