package br.com.guiromao.meli.mercadolivre.repository;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.util.ProductListConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {

    @Mock
    private ProductListConverter productListConverter;

    private ProductRepository productRepository;
    private List<Product> products = new ArrayList<>();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        products = List.of(
                Product.builder()
                        .id(UUID.fromString("3b2c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e"))
                        .name("Product 1")
                        .price(new BigDecimal(100))
                        .build(),
                Product.builder()
                        .id(UUID.fromString("3b2c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7f"))
                        .name("Product 2")
                        .price(new BigDecimal(200))
                        .build());
        productRepository = new ProductRepository(productListConverter);
    }

    @Test
    void retornaPaginaQuandoTemProdutos() {
        when(productListConverter.products()).thenReturn(products);

        var pageable = PageRequest.of(0, 2);
        var page = productRepository.getAllProducts(pageable);

        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getTotalElements()).isEqualTo(2);
    }

    @Test
    void RetornaPaginaQuandoNaoTemProdutos() {
        when(productListConverter.products()).thenReturn(List.of());

        var pageable = PageRequest.of(0, 2);
        var page = productRepository.getAllProducts(pageable);

        assertThat(page.getContent()).isEmpty();
        assertThat(page.getTotalElements()).isZero();
    }

    @Test
    void retornaProdutoQuandoIdExiste() {
        var id = UUID.fromString("3b2c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7f");
        when(productListConverter.products()).thenReturn(products);


        var optionalProduct = productRepository.getBy(id);

        assertThat(optionalProduct).isPresent();
        assertThat(optionalProduct.get().getId()).isEqualTo(id);
    }

    @Test
    void quandoIdNaoExiste() {
        var id = UUID.randomUUID();
        when(productListConverter.products()).thenReturn(products);

        var optionalProduct = productRepository.getBy(id);

        assertThat(optionalProduct).isEmpty();
    }

    @Test
    void retornaPaginaVaziaQuandoOffsetEstaForaDoRange() {
        when(productListConverter.products()).thenReturn(products);

        var pageable = PageRequest.of(10, 30);
        var page = productRepository.getAllProducts(pageable);

        org.assertj.core.api.Assertions.assertThat(page.getContent()).isEmpty();
        org.assertj.core.api.Assertions.assertThat(page.getTotalElements()).isEqualTo(2);
    }

}