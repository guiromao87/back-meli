package br.com.guiromao.meli.mercadolivre.controller;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.domain.enuns.Category;
import br.com.guiromao.meli.mercadolivre.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ProductController productController;

    private Product product;
    private Page<Product> page;

    @BeforeEach
    void setup() {
        product = Product.builder()
                .id(UUID.randomUUID())
                .name("Produto 1")
                .category(Category.BOOK)
                .price(BigDecimal.valueOf(99.99))
                .build();

        page = new PageImpl<>(Collections.singletonList(product), PageRequest.of(0, 10), 1);
    }

    @Test
    void shouldReturnPaginatedProducts() throws Exception {
        Mockito.when(productService.getAllProducts(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(product.getId().toString()))
                .andExpect(jsonPath("$.content[0].name").value(product.getName()))
                .andExpect(jsonPath("$.content[0].price").value(product.getPrice()));
    }

    @Test
    void shouldReturnProductById() throws Exception {
        Mockito.when(productService.getBy(product.getId())).thenReturn(Optional.ofNullable(product));

        mockMvc.perform(get("/api/v1/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.getId().toString()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    void getBy_ThrowsProductNotExistsException_WhenProductNotFound() {
        UUID productId = UUID.randomUUID();
        when(productService.getBy(productId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productController.getBy(productId))
                .isInstanceOf(br.com.guiromao.meli.mercadolivre.infra.exception.ProductNotExistsException.class)
                .hasMessage("Produto não encontrado");
    }
}