package pl.slowikowski.demo.product.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.slowikowski.demo.crud.product.Product;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductMapper;
import pl.slowikowski.demo.crud.product.ProductMapperImpl;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.slowikowski.demo.utils.Utils.getProduct;
import static pl.slowikowski.demo.utils.Utils.getProductDto;

//import pl.slowikowski.demo.product.ProductMapperImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductMapperTest {

    ProductMapper productMapper = ProductMapperImpl.INSTANCE;
    ProductGroup systemGroup;

    @BeforeAll
    void init() {
        systemGroup = ProductGroup.ProductGroupBuilder.aProductGroup()
                .withName("SYSTEM")
                .withDescription("SYSTEM")
                .withId(1L)
                .build();
    }

    @Test
    void should_map_product_to_productDto() {
        //given
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();
        productDTO.setGroupId(product.getGroup().getId());

        //when
        ProductDTO result = productMapper.toDto(product);

        //then
        assertEquals(productDTO,result);
    }

    @Test
    void should_map_productDto_to_product() {
        //given
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        //when
        Product result = productMapper.fromDto(productDTO);

        //then
        assertEquals(product, result);
    }

    @Test
    void should_map_productDto_to_product_and_assign_system_group_to_it() {
        //given
        Product product = getProduct();
        product.setGroup(systemGroup);

        ProductDTO productDTO = ProductDTO.ProductDTOBuilder.aProductDTO()
                .withId(product.getId())
                .withName(product.getName())
                .withDescription(product.getDescription())
                .withPrice(product.getPrice())
                .build();

        //when
        Product result = productMapper.fromDto(productDTO);

        //then
        assertEquals(product, result);
    }

    @Test
    void shouldnt_map_product_to_productDto() {
        //given
        Product product = getProduct();
        product.setId(123L);
        ProductDTO productDTO = getProductDto();

        //when
        ProductDTO result = productMapper.toDto(product);

        //then
        assertNotEquals(productDTO,result);
    }

    @Test
    void shouldnt_map_productDto_to_product() {
        //given
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();
        productDTO.setName("foo");

        //when
        Product result = productMapper.fromDto(productDTO);

        //then
        assertNotEquals(product, result);
    }

    @Test
    void should_map_correctly() {
        //given
        var toTest = getProduct();
        var product = getProduct();

        //when
        var productDTO = productMapper.toDto(product);
        var result  = productMapper.fromDto(productDTO);

        //then
        assertEquals(result, toTest);
    }
}
