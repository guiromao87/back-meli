package br.com.guiromao.meli.mercadolivre.controller.dto.response;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
