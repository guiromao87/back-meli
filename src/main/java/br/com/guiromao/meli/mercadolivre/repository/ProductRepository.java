package br.com.guiromao.meli.mercadolivre.repository;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.util.ProductLoader;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private static final List<Product> products;
    private static final Map<UUID, Product> productMap;

    static {
        products = ProductLoader.loadProducts();
        productMap = products.stream().collect(Collectors.toMap(Product::getId, product -> product));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findBy(UUID productId) {
        return productMap.get(productId);
    }
}
