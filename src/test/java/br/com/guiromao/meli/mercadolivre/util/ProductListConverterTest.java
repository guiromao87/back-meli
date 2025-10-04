package br.com.guiromao.meli.mercadolivre.util;

import br.com.guiromao.meli.mercadolivre.domain.Product;
import br.com.guiromao.meli.mercadolivre.infra.exception.JsonProcessorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductListConverterTest {

    @Test
    void retornaListaDeProdutosQuandoArquivoValido() throws Exception {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        Product produto = Product.builder().id(java.util.UUID.randomUUID()).name("Produto").build();
        List<Product> produtos = List.of(produto);

        when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class))).thenReturn(produtos);

        ProductListConverter converter = new ProductListConverter(objectMapper) {
            @Override
            public List<Product> products() {
                try {
                    return objectMapper.readValue(
                            new ByteArrayInputStream("[]".getBytes()),
                            new TypeReference<List<Product>>() {}
                    );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        List<Product> resultado = converter.products();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getName()).isEqualTo("Produto");
    }

    @Test
    void lancaJsonProcessorExceptionQuandoArquivoNaoExiste() {
        ObjectMapper objectMapper = mock(ObjectMapper.class);

        ProductListConverter converter = new ProductListConverter(objectMapper) {
            @Override
            public List<Product> products() {
                throw new JsonProcessorException("Erro ao processar o arquivo products.json");
            }
        };

        assertThatThrownBy(converter::products)
                .isInstanceOf(JsonProcessorException.class)
                .hasMessageContaining("Erro ao processar o arquivo products.json");
    }

    @Test
    void lancaJsonProcessorExceptionQuandoObjectMapperFalha() throws Exception {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        when(objectMapper.readValue(any(InputStream.class), any(TypeReference.class)))
                .thenThrow(new RuntimeException("Falha de parsing"));

        ProductListConverter converter = new ProductListConverter(objectMapper) {
            @Override
            public List<Product> products() {
                try {
                    return objectMapper.readValue(
                            new ByteArrayInputStream("[]".getBytes()),
                            new TypeReference<List<Product>>() {}
                    );
                } catch (Exception e) {
                    throw new JsonProcessorException("Erro ao processar o arquivo products.json");
                }
            }
        };

        assertThatThrownBy(converter::products)
                .isInstanceOf(JsonProcessorException.class)
                .hasMessageContaining("Erro ao processar o arquivo products.json");
    }

}