package br.com.guiromao.meli.mercadolivre.controller.dto.response;

import br.com.guiromao.meli.mercadolivre.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String category,
        String imageUrl,
        BigDecimal price
) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getCategory().getValue(), product.getImageUrl(), product.getPrice());
    }
}
