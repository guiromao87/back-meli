package br.com.guiromao.meli.mercadolivre.controller;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductDetailResponseDTO;
import br.com.guiromao.meli.mercadolivre.controller.dto.response.ProductResponseDTO;
import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.infra.exception.ProductNotExistsException;
import br.com.guiromao.meli.mercadolivre.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ReturnsOkWithProducts_WhenProductsExist() {
        Pageable pageable = PageRequest.of(0, 5);
        var product = mock(Product.class);
        Page<Product> page = new PageImpl<>(List.of(product));
        when(productService.getAllProducts(pageable)).thenReturn(page);

        ResponseEntity<Page<ProductResponseDTO>> response = productController.getAllProducts(pageable);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSize(1);
    }

    @Test
    void getAllProducts_ReturnsNoContent_WhenNoProductsExist() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> emptyPage = Page.empty();
        when(productService.getAllProducts(pageable)).thenReturn(emptyPage);

        ResponseEntity<Page<ProductResponseDTO>> response = productController.getAllProducts(pageable);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }

    @Test
    void getBy_ReturnsProductDetail_WhenProductExists() {
        UUID productId = UUID.randomUUID();
        var product = mock(Product.class);
        when(productService.getBy(productId)).thenReturn(Optional.of(product));

        ResponseEntity<ProductDetailResponseDTO> response = productController.getBy(productId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void getBy_ThrowsException_WhenProductDoesNotExist() {
        UUID productId = UUID.randomUUID();
        when(productService.getBy(productId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productController.getBy(productId))
                .isInstanceOf(ProductNotExistsException.class)
                .hasMessageContaining("Produto não encontrado");
    }
}
