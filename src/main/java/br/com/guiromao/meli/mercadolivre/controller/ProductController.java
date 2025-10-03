package br.com.guiromao.meli.mercadolivre.controller;

import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductDetailResponseDTO;
import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductResponseDTO;
import br.com.guiromao.meli.mercadolivre.infra.exception.ProductNotExistsException;
import br.com.guiromao.meli.mercadolivre.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Get all products with pagination",
            description = "Returns a paginated list of products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Paginated list of products retrieved successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@PageableDefault(size = 5) Pageable pageable) {
        var products = this.productService.getAllProducts(pageable);

        log.info("Getting products {} ", products);

        if(products.isEmpty())
            ResponseEntity.noContent().build();

        log.info("Success getting products");

        return ResponseEntity.ok(products.map(ProductResponseDTO::new));

    }

    @Operation(summary = "Get product by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product found"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponseDTO> getBy(@PathVariable UUID productId) {
        var optional = this.productService.getBy(productId);

        log.info("Getting product by id {} ", productId);

        if(optional.isEmpty()) {
            throw new ProductNotExistsException("Produto não encontrado");
        }

        log.info("Success getting product by id - Product: {}.", optional.get());

        return ResponseEntity.ok(new ProductDetailResponseDTO(optional.get()));
    }
}
