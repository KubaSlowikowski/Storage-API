package pl.slowikowski.demo.productGroup.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.slowikowski.demo.product.Product;
import pl.slowikowski.demo.product.ProductMapper;
import pl.slowikowski.demo.product.ProductRepository;
import pl.slowikowski.demo.productGroup.ProductGroup;
import pl.slowikowski.demo.productGroup.ProductGroupMapper;
import pl.slowikowski.demo.productGroup.ProductGroupRepository;
import pl.slowikowski.demo.productGroup.ProductGroupServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.slowikowski.demo.utils.Utils.getProductGroup;
import static pl.slowikowski.demo.utils.Utils.getProductGroupDto;

@RunWith(MockitoJUnitRunner.class)
class ProductGroupServiceImplTest {

    @Mock
    ProductGroupRepository mockProductGroupRepository;

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
    void should_find_productGroup_by_id() {
        //given
        var group = getProductGroup();
        when(mockProductGroupRepository.findById(anyInt())).thenReturn(Optional.of(group));
        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, null, mapper, productMapper);
        //when
        var result = toTest.findById(anyInt());
        //then
        assertThat(mapper.groupDtoToGroup(result)).isEqualTo(group);
    }

    @Test
    void should_save_productGroup() {
        //given
        var productGroup = getProductGroup();
        var productGroupDto = getProductGroupDto();
        //and
        when(mockProductGroupRepository.save(any(ProductGroup.class))).thenReturn(productGroup);
        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, null, mapper, productMapper);

        //when
        var result = toTest.saveProductGroup(productGroupDto);

        //then
        assertThat(mapper.groupDtoToGroup(result)).isEqualTo(productGroup);
    }

    @Test
    void should_update_product_group() {
        //given
        var productGroup = getProductGroup();
        var modifiedProduct = getProductGroup();
        modifiedProduct.setDescription("changedDesc");

        //and
        when(mockProductGroupRepository.findById(anyInt())).thenReturn(Optional.of(productGroup));

        //and
        var mockProductRepository = mock(ProductRepository.class);
        when(mockProductRepository.save(any(Product.class))).thenReturn(null);

        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, mockProductRepository, mapper, productMapper);

        //when
        var result = toTest.updateGroup(productGroup.getId(), mapper.groupToGroupDto(modifiedProduct));

        //then
        assertThat(mapper.groupDtoToGroup(result)).isEqualTo(modifiedProduct);

    }

    @Test
    void should_delete_productGroup_by_id() {
        //given
        var productGroup = getProductGroup();

        //and
        when(mockProductGroupRepository.findById(anyInt())).thenReturn(Optional.of(productGroup));

        //system under test
        var toTest = new ProductGroupServiceImpl(mockProductGroupRepository, null, mapper, productMapper);

        //when
        var result = toTest.deleteProductById(productGroup.getId());

        //then
        assertThat(mapper.groupDtoToGroup(result)).isEqualTo(productGroup);
    }
}
