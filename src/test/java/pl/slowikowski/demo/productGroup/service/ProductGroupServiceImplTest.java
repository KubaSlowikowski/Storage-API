package pl.slowikowski.demo.productGroup.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.util.ReflectionTestUtils;
import pl.slowikowski.demo.crud.product.Product;
import pl.slowikowski.demo.crud.product.ProductMapper;
import pl.slowikowski.demo.crud.product.ProductRepository;
import pl.slowikowski.demo.crud.productGroup.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.slowikowski.demo.utils.Utils.getProductGroup;
import static pl.slowikowski.demo.utils.Utils.getProductGroupDto;

@RunWith(MockitoJUnitRunner.class)
class ProductGroupServiceImplTest {

    @Mock
    ProductGroupRepository mockProductGroupRepository;

    @Mock
    ProductRepository mockProductRepository;

    @Spy
    ProductGroupMapper mapper = ProductGroupMapper.INSTANCE;

    @Spy
    ProductMapper productMapper = ProductMapper.INSTANCE;

    @BeforeEach
    public void init_mocks() {
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(mapper, "productMapper", productMapper);
    }

    @BeforeEach
    void init_mocks2() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_find_all_productGroups() {
        //given
        List<ProductGroupDTO> dtoList = List.of(getProductGroupDto());
        List<ProductGroup> list = List.of(getProductGroup());

        //and
        when(mockProductGroupRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(list));

        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, null, mapper, productMapper);

        //when
        assertThat(toTest.getAll(Specification.where(null), Pageable.unpaged()).getContent())
                .isEqualTo(dtoList)
                .hasSize(list.size());

        //then
    }

    @Test
    void should_find_productGroup_by_id() {
        //given
        var group = getProductGroup();
        when(mockProductGroupRepository.findById(anyLong())).thenReturn(Optional.of(group));
        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, null, mapper, productMapper);
        //when
        var result = toTest.findById(anyLong());
        //then
        assertThat(mapper.fromDto(result)).isEqualTo(group);
    }

    @Test
    void should_save_productGroup() {
        //given
        var productGroup = getProductGroup();
        var productGroupDto = getProductGroupDto();
        //and
        when(mockProductGroupRepository.saveAndFlush(any(ProductGroup.class))).thenReturn(productGroup);
        when(mockProductRepository.saveAndFlush(any(Product.class))).thenReturn(null);

        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, mockProductRepository, mapper, productMapper);

        //when
        var result = toTest.save(productGroupDto);

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(productGroup);
    }

    @Test
    void should_update_product_group() {
        //given
        var productGroup = getProductGroup();
        var modifiedProduct = getProductGroup();
        modifiedProduct.setDescription("changedDesc");

        //and
        when(mockProductGroupRepository.findById(anyLong())).thenReturn(Optional.of(productGroup));

        //and
        var mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.saveAndFlush(any(Product.class))).thenReturn(null);

        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, mockProductRepository, mapper, productMapper);

        //when
        var result = toTest.update(productGroup.getId(), mapper.toDto(modifiedProduct));

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(modifiedProduct);

    }

    @Test
    void should_delete_productGroup_by_id() {
        //given
        var productGroup = getProductGroup();

        //and
        when(mockProductGroupRepository.findById(anyLong())).thenReturn(Optional.of(productGroup));
        when(mockProductRepository.findAllByGroup_Id(anyLong())).thenReturn(Collections.emptyList());

        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, mockProductRepository, mapper, productMapper);

        //when
        var result = toTest.delete(productGroup.getId());

        //then
        assertThat(mapper.fromDto(result)).isEqualTo(productGroup);
    }
}
