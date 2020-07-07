package pl.slowikowski.demo.persistence.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void should_toogle_product() {
        //given
        var toTest = new Product();

        //when
        toTest.toogle();

        //then
        assertThat(toTest.isSold()).isTrue();
    }

    @Test
    void should_update_product() {
        //given
        var toTest = new Product();

        //when
        toTest.updateFrom(new Product("foo", null, 0));

        //then
        assertThat(toTest.getName()).isEqualTo("foo");
        assertThat(toTest.getDescription()).isNull();
        assertThat(toTest.isSold()).isFalse();
        assertThat(toTest.getPrice()).isZero();

        //when
        toTest = new Product();
        toTest.updateFrom(new Product("foo", "bar", 123));

        //then
        assertThat(toTest.getName()).isEqualTo("foo");
        assertThat(toTest.getDescription()).isEqualTo("bar");
        assertThat(toTest.isSold()).isFalse();
        assertThat(toTest.getPrice()).isEqualTo(123);

        //when
        toTest = new Product();
        toTest.updateFrom(new Product(null, null, 0));

        //then
        assertThat(toTest.getName()).isNull();
        assertThat(toTest.getDescription()).isNull();
        assertThat(toTest.isSold()).isFalse();
        assertThat(toTest.getPrice()).isZero();
    }
}