package pl.slowikowski.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.persistence.repository.ProductGroupRepository;
import pl.slowikowski.demo.persistence.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRepositoryImplTest {
    @Test
    void should_find_All_products() {
        //given
        var list = List.of(new Product("foo", "bar", 123));
        //and
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.findAll()).thenReturn(list);

        //system under test
        var toTest = new ProductRepositoryImpl(mockProductRepository, null);

        //when +then
        assertThat(toTest.findAll()).isEqualTo(list);
    }

    @Test
    void should_save_product() {
        //given
        var product = new Product("foo", "bar", 123);
        //and
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.save(product)).thenReturn(product);

        //system under test
        var toTest = new ProductRepositoryImpl(mockProductRepository, null);

        //when
        var result = toTest.save(product);

        //then
        assertThat(result.getName()).isEqualTo("foo");
        assertThat(result.getDescription()).isEqualTo("bar");
        assertThat(result.getPrice()).isEqualTo(123);
    }

    @Test
    void should_not_find_product_by_id_and_throw_HttpClientErrorException() {
        //given
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.existsById(anyInt())).thenReturn(false);

        //system under test
        var toTest = new ProductRepositoryImpl(mockProductRepository, null);

        //when
        var exception = catchThrowable(() -> toTest.findById(anyInt()));

        //then
        assertThat(exception)
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("404 Product does not exist!");
    }

    @Test
    void should_find_product_by_id() {
        //given
        var product = new Product("foo", "bar", 123);

        //and
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.existsById(anyInt())).thenReturn(true);
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));

        //system under test
        var toTest = new ProductRepositoryImpl(mockProductRepository, null);

        //when
        var result = toTest.findById(anyInt());

        //then
        assertThat(result.getName()).isEqualTo("foo");
        assertThat(result.getDescription()).isEqualTo("bar");
        assertThat(result.getPrice()).isEqualTo(123);
    }

//    @Test
//    void should_buy_product_by_id() {
//        //given
//        var product = new Product("foo", "bar", 123);
//
//        //and
//        ProductRepository mockProductRepository = mock(ProductRepository.class);
//        when(mockProductRepository.existsById(anyInt())).thenReturn(true);
//        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));
//        when(mockProductRepository.save(product)).thenReturn(product);
//
//        //system under test
//        var toTest = new ProductRepositoryImpl(mockProductRepository, null);
//
//        //when
//        var result = toTest.findById(anyInt());
//
//        //then
//        assertThat(result.getName()).isEqualTo("foo");
//        assertThat(result.getDescription()).isEqualTo("bar");
//        assertThat(result.getPrice()).isEqualTo(123);
//        assertThat(result.isSold()).isTrue();
//    }

    @Test
    void should_not_find_products_by_group_id_and_throw_HttpClientErrorException() {
        //given
        var product = new Product("foo", "bar", 123);

        //and
        ProductGroupRepository mockProductGroupRepository = mock(ProductGroupRepository.class);
        when(mockProductGroupRepository.existsById(anyInt())).thenReturn(false);

        //system under test
        var toTest = new ProductRepositoryImpl(null, mockProductGroupRepository);

        //when
        var exception = catchThrowable(() -> toTest.findAllByGroupId(anyInt()));

        //then
        assertThat(exception)
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("404 Product group does not exist!");
    }

    @Test
    void should_find_products_by_group_id() {
        //given
        var product = new Product("foo", "bar", 123);

        //and
        ProductRepository mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.findAllByGroup_Id(anyInt())).thenReturn(List.of(product));

        //and
        ProductGroupRepository mockProductGroupRepository = mock(ProductGroupRepository.class);
        when(mockProductGroupRepository.existsById(anyInt())).thenReturn(true);

        //system under test
        var toTest = new ProductRepositoryImpl(mockProductRepository, mockProductGroupRepository);

        //when
        var result = toTest.findAllByGroupId(anyInt());

        //then
        assertThat(result)
                .hasSize(1)
                .containsExactly(product);
    }
}