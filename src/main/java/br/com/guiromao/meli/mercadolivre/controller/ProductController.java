package br.com.guiromao.meli.mercadolivre.controller;

import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductDetailResponseDTO;
import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductResponseDTO;
import br.com.guiromao.meli.mercadolivre.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@PageableDefault(size = 5) Pageable pageable) {
        var products = this.productService.getAllProducts(pageable);

        if(products.isEmpty())
            ResponseEntity.noContent().build();

        return ResponseEntity.ok(products.map(ProductResponseDTO::new));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponseDTO> getBy(@PathVariable UUID productId) {
        var optional = this.productService.getBy(productId);

        if(optional.isEmpty()) {
            throw new RuntimeException("");
        }

        return ResponseEntity.ok(new ProductDetailResponseDTO(optional.get()));
    }
}
