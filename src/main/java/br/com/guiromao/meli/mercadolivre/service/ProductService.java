package br.com.guiromao.meli.mercadolivre.service;


import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Value("${products.path}")
    private String productsPath;

    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.getAllProducts(productsPath, pageable);
    }
}
