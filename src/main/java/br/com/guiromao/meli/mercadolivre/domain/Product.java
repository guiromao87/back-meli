package br.com.guiromao.meli.mercadolivre.domain;

import br.com.guiromao.meli.mercadolivre.domain.enuns.Category;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@ToString
@Data
public class Product {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Category category;
    private double review;
}
