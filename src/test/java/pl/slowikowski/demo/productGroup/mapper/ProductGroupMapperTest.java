package pl.slowikowski.demo.productGroup.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.slowikowski.demo.crud.product.ProductMapper;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroupMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.slowikowski.demo.utils.Utils.getProductGroup;
import static pl.slowikowski.demo.utils.Utils.getProductGroupDto;

@RunWith(MockitoJUnitRunner.class)
class ProductGroupMapperTest {

    @InjectMocks
    ProductGroupMapper groupMapper = Mappers.getMapper(ProductGroupMapper.class);

    @BeforeEach
    public void setUp() {
        ProductMapper productMapper = Mappers.getMapper(ProductMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(groupMapper, "productMapper", productMapper);
    }

    @Test
    void should_map_productGroup_to_productGroupDto() {
        //given
        ProductGroup group = getProductGroup();
        ProductGroupDTO groupDto = getProductGroupDto();
        //when
        ProductGroupDTO result = groupMapper.toDto(group);
        //then
        assertEquals(groupDto, result);
    }

    @Test
    void should_map_productGroupDto_to_productGroup() {
        //given
        ProductGroup group = getProductGroup();
        ProductGroupDTO groupDto = getProductGroupDto();
        //when
        ProductGroup result = groupMapper.fromDto(groupDto);
        //then
        assertEquals(group, result);
    }

    @Test
    void shouldnt_map_productGroup_to_productGroupDto() {
        //given
        ProductGroup group = getProductGroup();
        group.setName("foo");
        ProductGroupDTO groupDto = getProductGroupDto();
        //when
        ProductGroupDTO result = groupMapper.toDto(group);
        //then
        assertNotEquals(groupDto, result);
    }

    @Test
    void shouldnt_map_productGroupDto_to_productGroup() {
        //given
        ProductGroup group = getProductGroup();
        ProductGroupDTO groupDto = getProductGroupDto();
        groupDto.setName("foo");
        //when
        ProductGroup result = groupMapper.fromDto(groupDto);
        //then
        assertNotEquals(group, result);
    }
}
