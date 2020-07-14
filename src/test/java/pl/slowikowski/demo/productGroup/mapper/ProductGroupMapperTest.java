package pl.slowikowski.demo.productGroup.mapper;

import org.junit.jupiter.api.Test;
import pl.slowikowski.demo.productGroup.ProductGroup;
import pl.slowikowski.demo.productGroup.ProductGroupMapper;
import pl.slowikowski.demo.productGroup.ProductGroupDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pl.slowikowski.demo.utils.Utils.getProductGroup;
import static pl.slowikowski.demo.utils.Utils.getProductGroupDto;

class ProductGroupMapperTest {

    ProductGroupMapper groupMapper = ProductGroupMapper.INSTANCE;

    @Test
    void should_map_productGroup_to_productGroupDto() {
        //given
        ProductGroup group = getProductGroup();
        ProductGroupDTO groupDto = getProductGroupDto();
        //when
        ProductGroupDTO result = groupMapper.groupToGroupDto(group);
        //then
        assertEquals(groupDto, result);
    }

    @Test
    void should_map_productGroupDto_to_productGroup() {
        //given
        ProductGroup group = getProductGroup();
        ProductGroupDTO groupDto = getProductGroupDto();
        //when
        ProductGroup result = groupMapper.groupDtoToGroup(groupDto);
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
        ProductGroupDTO result = groupMapper.groupToGroupDto(group);
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
        ProductGroup result = groupMapper.groupDtoToGroup(groupDto);
        //then
        assertNotEquals(group, result);
    }
}
