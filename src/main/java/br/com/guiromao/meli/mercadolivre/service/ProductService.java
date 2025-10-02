package br.com.guiromao.meli.mercadolivre.service;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.repository.ProductRepository;
import br.com.guiromao.meli.mercadolivre.util.ProductLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return ProductLoader.loadProducts();
    }

    public Product findBy(UUID productId) {
        return this.productRepository.findBy(productId);
    }
}
