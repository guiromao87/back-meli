package br.com.guiromao.meli.mercadolivre;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.repository.ProductRepository;
import br.com.guiromao.meli.mercadolivre.service.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductService.class})
class ProductServiceTest {

    @MockitoBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(UUID.randomUUID())
                .name("Product name")
                .price(BigDecimal.TEN)
                .build();
    }

    @Test
    void getAllProducts_ReturnsPageWithProducts_WhenProductsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(product));

        when(productRepository.getAllProducts(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(product);
    }

    @Test
    void getAllProducts_ReturnsEmptyPage_WhenNoProductsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of());

        when(productRepository.getAllProducts(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertThat(result).isEmpty();
    }

    @Test
    void getBy_ReturnsProductOptional_WhenProductExists() {
        when(productRepository.getBy(product.getId())).thenReturn(Optional.of(product));
        Optional<Product> result = productService.getBy(product.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

    @Test
    void getBy_ReturnsEmptyOptional_WhenProductDoesNotExist() {
        when(productRepository.getBy(product.getId())).thenReturn(Optional.empty());

        Optional<Product> result = productService.getBy(product.getId());

        assertThat(result).isEmpty();
    }
}
