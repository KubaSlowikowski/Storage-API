package pl.slowikowski.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.model.ProductGroupDTO;
import pl.slowikowski.demo.persistence.model.ProductGroup;
import pl.slowikowski.demo.persistence.repository.ProductGroupRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductGroupRepositoryImplTest {

    @Test
    void should_find_All_groups() {
        //given
        var list = List.of(new ProductGroup("foo", "bar", null));
        //and
        ProductGroupRepository mockProductRepository = mock(ProductGroupRepository.class);
        when(mockProductRepository.findAll()).thenReturn(list);

        //system under test
        var toTest = new ProductGroupRepositoryImpl(mockProductRepository);

        //when +then
        assertThat(toTest.findAll()).isEqualTo(list);
    }

    @Test
    void should_save_group() {
        //given
        var group = new ProductGroup("foo", "bar", null);

        //and
        ProductGroupRepository mockProductRepository = mock(ProductGroupRepository.class);
        when(mockProductRepository.save(any())).thenReturn(group);

        //system under test
        var toTest = new ProductGroupRepositoryImpl(mockProductRepository);

        //when
        var result = toTest.save(group);

        //then
        assertThat(result.getName()).isEqualTo("foo");
        assertThat(result.getDescription()).isEqualTo("bar");
        assertThat(result.getProducts()).isNull();
    }

    @Test
    void should_not_find_group_by_id_and_throw_HttpClientErrorException() {
        //given
        ProductGroupRepository mockProductRepository = mock(ProductGroupRepository.class);
        when(mockProductRepository.existsById(anyInt())).thenReturn(false);

        //system under test
        var toTest = new ProductGroupRepositoryImpl(mockProductRepository);

        //when
        var exception = catchThrowable(() -> toTest.findById(anyInt()));

        //then
        assertThat(exception)
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("404 Product does not exist!");
    }

    @Test
    void should_find_group_by_id() {
        //given
        var group = new ProductGroup("foo", "bar", null);
        var groupDTO = new ProductGroupDTO(group);

        //and
        ProductGroupRepository mockProductRepository = mock(ProductGroupRepository.class);
        when(mockProductRepository.existsById(anyInt())).thenReturn(true);
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(group));

        //system under test
        var toTest = new ProductGroupRepositoryImpl(mockProductRepository);

        //when
        var result = toTest.findById(anyInt());

        //then
        assertThat(result.getName()).isEqualTo("foo");
        assertThat(result.getDescription()).isEqualTo("bar");
        assertThat(result.getProducts()).isNull();
    }

    @Test
    void should_not_update_group_by_id_and_throw_HttpClientErrorException() {
        //given
        ProductGroupRepository mockProductRepository = mock(ProductGroupRepository.class);
        when(mockProductRepository.existsById(anyInt())).thenReturn(false);

        //system under test
        var toTest = new ProductGroupRepositoryImpl(mockProductRepository);

        //when
        var exception = catchThrowable(() -> toTest.updateGroup(anyInt(),null));

        //then
        assertThat(exception)
                .isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("404 Product group does not exist!");
    }

//    @Test
//    void should_update_group_by_id() {
//        //given
//        var group = new ProductGroup("foo", "bar", null);
//        var groupDTO = new ProductGroupDTO(group);
//
//        //and
//        ProductGroupRepository mockProductRepository = mock(ProductGroupRepository.class);
//        when(mockProductRepository.existsById(anyInt())).thenReturn(true);
//        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(group));
//
//        //system under test
//        var toTest = new ProductGroupRepositoryImpl(mockProductRepository);
//
//        //when
//        var result = toTest.updateGroup(anyInt(), group);
//
//        //then
////        assertThat(result.getName()).isEqualTo("foo");
////        assertThat(result.getDescription()).isEqualTo("bar");
////        assertThat(result.getProducts()).isNull();
    //}
}