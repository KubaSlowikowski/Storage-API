package pl.slowikowski.demo.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.slowikowski.demo.exception.NotFoundException;
import pl.slowikowski.demo.product.*;
import pl.slowikowski.demo.productGroup.ProductGroupRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.slowikowski.demo.utils.Utils.*;

class ProductServiceImplTest {

    @Mock
    ProductRepository mockProductRepository;

    private ProductMapper mapper = ProductMapper.INSTANCE;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_find_all_products() {
        //given
        List<ProductDTO> dtoList = List.of(getProductDto());
        List<Product> list = List.of(getProduct());

        //and
        when(mockProductRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(list));

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when +then
        assertThat(toTest.getAll(Specification.where(null), Pageable.unpaged()).getContent())
                .isEqualTo(dtoList)
                .hasSize(list.size());
    }

    @Test
    void should_not_find_product_by_id_and_throw_HttpClientErrorException() {
        //given
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.empty());

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when
        var exception = catchThrowable(() -> toTest.findById(anyInt()));

        //then
        assertThat(exception)
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void should_find_product_by_id() {
        //given
        var product = getProduct();

        //and
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when
        var result = toTest.findById(anyInt());

        //then

        assertThat(mapper.fromDto(result)).isEqualTo(product);
    }

    @Test
    void should_save_product() {
        //given
        var product = getProduct();
        var productDto = getProductDto();
        //and
        when(mockProductRepository.saveAndFlush(any(Product.class))).thenReturn(product);

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when
        var result = toTest.save(productDto);

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(product);
    }

    @Test
    void should_update_product() {
        //given
        var product = getProduct();
        var modifiedProduct = getProduct();
        modifiedProduct.setDescription("qwerty");
        //and
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));
        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when
        var result = toTest.update(product.getId(), mapper.toDto(modifiedProduct));

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(modifiedProduct);
    }

    @Test
    void should_delete_product() {
        //given
        var product = getProduct();
        //and
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when
        var result = toTest.delete(product.getId());

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(product);
    }

    @Test
    void should_buy_project_by_id() {
        //given
        var product = getProduct();
        var changedProduct = getProduct();
        changedProduct.setSold(!changedProduct.isSold());
        //and
        when(mockProductRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(mockProductRepository.saveAndFlush(changedProduct)).thenReturn(changedProduct);
        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, null, mapper);

        //when
        var result = toTest.buyProduct(product.getId());

        //and
        product.setSold(!product.isSold());

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(product);
    }

    @Test
    void should_find_products_by_group_id() {
        //given
        var product = getProduct();

        //and
        when(mockProductRepository.findAllByGroup_Id(anyInt())).thenReturn(List.of(product));

        //and
        ProductGroupRepository mockProductGroupRepository = mock(ProductGroupRepository.class);
        when(mockProductGroupRepository.findById(anyInt())).thenReturn(Optional.of(getProductGroup()));

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, mockProductGroupRepository, mapper);

        //when
        var result = toTest.findAllByGroupId(anyInt());

        //then
        assertThat(result)
                .hasSize(1)
                .containsExactly(mapper.toDto(product));
    }

    @Test
    void should_not_find_products_by_group_id_and_throw_HttpClientErrorException() {
        //given
        var product = getProduct();

        //and
        ProductGroupRepository mockProductGroupRepository = mock(ProductGroupRepository.class);

        //system under test
        var toTest = new ProductServiceImpl(mockProductRepository, mockProductGroupRepository, mapper);

        //when
        var exception = catchThrowable(() -> toTest.findAllByGroupId(anyInt()));

        //then
        assertThat(exception)
                .isInstanceOf(NotFoundException.class);
    }
}
