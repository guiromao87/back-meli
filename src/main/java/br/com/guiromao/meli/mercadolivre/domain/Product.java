package br.com.guiromao.meli.mercadolivre.domain;

import br.com.guiromao.meli.mercadolivre.domain.enuns.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Category category;
    private double review;
}
