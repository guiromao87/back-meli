package br.com.guiromao.meli.mercadolivre.controller;

import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductResponseDTO;
import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        var products = this.productService.getAllProducts();
        return ResponseEntity.ok(products.stream().map(ProductResponseDTO::new).toList());
    }

    @GetMapping("/{productId}")
    public Product getBy(@PathVariable UUID productId) {
        return this.productService.findBy(productId);
    }

}
