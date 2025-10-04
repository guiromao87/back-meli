package br.com.guiromao.meli.mercadolivre.repository;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.infra.exception.JsonProcessorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class ProductRepository {

    @Value("${products.path}")
    private String productsPath;

    private final ObjectMapper objectMapper;
    private List<Product> listaDeProdutos;




    public ProductRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return returnPage(products(productsPath), pageable);
    }

    public Optional<Product> getBy(UUID productId) {
        var products = products(productsPath);
        return products.stream()
                .filter(produto -> produto.getId().equals(productId))
                .findFirst();
    }

    private List<Product> products(String productsPath) {
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


    private Page<Product> returnPage(List<Product> products, Pageable pageable) {
        var start = (int) pageable.getOffset();
        var end = Math.min(start + pageable.getPageSize(), products.size());

        var page = products.subList(start, end);

        return new PageImpl<>(page, pageable, products.size());
    }
}
