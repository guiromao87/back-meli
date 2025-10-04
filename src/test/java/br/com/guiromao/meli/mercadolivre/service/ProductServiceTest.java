package br.com.guiromao.meli.mercadolivre.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.data.domain.*;
import java.util.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnsPageWithProductsWhenProductsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Product product = mock(Product.class);
        Page<Product> page = new PageImpl<>(List.of(product));
        when(productRepository.getAllProducts(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(product);
    }

    @Test
    void returnsEmptyPageWhenNoProductsExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyPage = Page.empty();
        when(productRepository.getAllProducts(pageable)).thenReturn(emptyPage);

        Page<Product> result = productService.getAllProducts(pageable);

        assertThat(result).isEmpty();
    }

    @Test
    void returnsProductOptionalWhenProductExists() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(productRepository.getBy(id)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getBy(id);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

    @Test
    void returnsEmptyOptionalWhenProductDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(productRepository.getBy(id)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getBy(id);

        assertThat(result).isEmpty();
    }
}
