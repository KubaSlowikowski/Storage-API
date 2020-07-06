package pl.slowikowski.demo.persistence.model;

import org.junit.jupiter.api.Test;
import pl.slowikowski.demo.model.ProductDTO;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDTOTest {
    @Test
    void should_create_ProductDTO() {
        //given
        var source = new Product("foo", "bar", 123);
        //when
        var result = new ProductDTO(source);
        //then
        assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "foo")
                .hasFieldOrPropertyWithValue("description", "bar")
                .hasFieldOrPropertyWithValue("price", 123)
                .hasFieldOrPropertyWithValue("sold", false);
    }

    @Test
    void should_convert_from_ProductDTO_to_Product() {
        //given
        var source = new ProductDTO(new Product("foo", "bar", 123));
        //when
        var result = source.toProduct();
        //then
        assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "foo")
                .hasFieldOrPropertyWithValue("description", "bar")
                .hasFieldOrPropertyWithValue("price", 123)
                .hasFieldOrPropertyWithValue("sold", false);
    }
}