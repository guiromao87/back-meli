package br.com.guiromao.meli.mercadolivre.repository;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.util.ProductListConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepository {

    private final ProductListConverter productListConverter;

    public ProductRepository(ProductListConverter productListConverter) {
        this.productListConverter = productListConverter;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        var products = productListConverter.products();

        var start = (int) pageable.getOffset();
        var end = Math.min(start + pageable.getPageSize(), products.size());

        if (start > products.size()) {
            return new PageImpl<>(List.of(), pageable, products.size());
        }

        return new PageImpl<>(
                products.subList(start, end),
                pageable,
                products.size());
    }

    public Optional<Product> getBy(UUID productId) {
        var products = productListConverter.products();
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }
}
