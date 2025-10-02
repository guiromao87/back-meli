package br.com.guiromao.meli.mercadolivre.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.*;

public class ProductJsonCreateUtil {
    public static void main(String[] args) throws Exception {
        Faker faker = new Faker();
        List<Map<String, Object>> products = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Map<String, Object> product = new LinkedHashMap<>();
            product.put("id", UUID.randomUUID().toString());
            product.put("name", faker.commerce().productName());
            product.put("description", faker.lorem().sentence());
            product.put("imageUrl", "http://example.com/" + faker.internet().slug() + ".jpg");
            product.put("price", BigDecimal.TWO);
            product.put("category", "Electronics");
            product.put("review", faker.number().numberBetween(1, 5));
            products.add(product);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(products));
    }
}
