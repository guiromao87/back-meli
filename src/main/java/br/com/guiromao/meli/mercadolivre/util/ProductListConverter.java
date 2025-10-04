package br.com.guiromao.meli.mercadolivre.util;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.infra.exception.JsonProcessorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;

@Component
public class ProductListConverter {

    private final ObjectMapper objectMapper;

    public ProductListConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Product> products() {
        try {
            ClassPathResource resource = new ClassPathResource("products.json");

            try (InputStream inputStream = resource.getInputStream()) {
                return objectMapper.readValue(
                        inputStream,
                        new TypeReference<List<Product>>() {}
                );
            }

        } catch (Exception e) {
            throw new JsonProcessorException("Erro ao processar o arquivo products.json");
        }
    }
}
