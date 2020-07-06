package pl.slowikowski.demo.persistence.model;

import org.junit.jupiter.api.Test;
import pl.slowikowski.demo.model.ProductGroupDTO;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ProductGroupDTOTest {
    @Test
    void should_create_ProductGroupDTO() {
        //given
        var set = Set.of(new Product("foo", "bar", 123));
        var source = new ProductGroup("foo", "bar", set);
        //when
        var result = new ProductGroupDTO(source);
        //then
        assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "foo")
                .hasFieldOrPropertyWithValue("description", "bar")
                .hasFieldOrPropertyWithValue("products", set);
    }

    @Test
    void should_convert_from_ProductGroupDTO_to_ProductGroup() {
        //given
        var set = Set.of(new Product("foo", "bar", 123));
        var source = new ProductGroupDTO(new ProductGroup("foo", "bar", set));
        //when
        var result = source.toGroup();
        //then
        assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", "foo")
                .hasFieldOrPropertyWithValue("description", "bar")
                .hasFieldOrPropertyWithValue("products", set);
    }
}