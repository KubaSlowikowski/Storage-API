package pl.slowikowski.demo.mapper;

import org.junit.jupiter.api.Test;
import pl.slowikowski.demo.entity.ProductGroup;
import pl.slowikowski.demo.model.ProductGroupDTO;

import static org.junit.jupiter.api.Assertions.*;
import static pl.slowikowski.demo.utils.Utils.getProductGroupDto;
import static pl.slowikowski.demo.utils.Utils.getProductGroup;

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
