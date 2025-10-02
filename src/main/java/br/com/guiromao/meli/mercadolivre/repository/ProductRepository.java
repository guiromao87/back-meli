package br.com.guiromao.meli.mercadolivre.repository;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.infra.exception.JsonProcessorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class ProductRepository {

    public Page<Product> getAllProducts(String productsPath, Pageable pageable) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            var products = objectMapper.readValue(
                    new File(productsPath),
                    new TypeReference<List<Product>>() {}
            );

            return returnPage(products, pageable);

        } catch (IOException e) {
            throw new JsonProcessorException("Erro ao ler ou processar o arquivo JSON");
        }
    }

    private Page<Product> returnPage(List<Product> products, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), products.size());

        List<Product> page = products.subList(start, end);

        return new PageImpl<>(page, pageable, products.size());
    }
}
