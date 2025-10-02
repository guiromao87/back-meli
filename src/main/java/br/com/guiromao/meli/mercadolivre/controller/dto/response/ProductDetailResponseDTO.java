package br.com.guiromao.meli.mercadolivre.controller.dto.response;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductDetailResponseDTO(
        UUID id,
        String name,
        String description,
        String imageUrl,
        BigDecimal price,
        String category,
        double review
) {
    public ProductDetailResponseDTO(Product product) {
        this(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getImageUrl(),
            product.getPrice(),
            product.getCategory().getValue(),
            product.getReview()
        );
    }
}