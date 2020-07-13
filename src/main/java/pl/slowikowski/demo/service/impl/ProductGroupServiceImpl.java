package pl.slowikowski.demo.service.impl;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.entity.ProductGroup;
import pl.slowikowski.demo.exception.NotFoundException;
import pl.slowikowski.demo.mapper.ProductGroupMapper;
import pl.slowikowski.demo.model.ProductGroupDTO;
import pl.slowikowski.demo.repository.ProductGroupRepository;
import pl.slowikowski.demo.service.ProductGroupService;

import java.util.List;

public class ProductGroupServiceImpl implements ProductGroupService {

    private ProductGroupRepository repository;
    private final ProductGroupMapper groupMapper;

    public ProductGroupServiceImpl(final ProductGroupRepository repository, final ProductGroupMapper groupMapper) {
        this.repository = repository;
        this.groupMapper = groupMapper;
    }

    @Override
    public List<ProductGroupDTO> findAllProductGroups(Pageable page) {
        List<ProductGroup> result = repository.findAll(page).getContent();
        return groupMapper.groupListToGroupDtoList(result);
    }

    @Override
    public ProductGroupDTO findById(int id) {
        ProductGroup result = getGroupById(id);
        return groupMapper.groupToGroupDto(result);
    }

    @Override
    public ProductGroupDTO saveProductGroup(ProductGroupDTO toCreate) {
        ProductGroup group = groupMapper.groupDtoToGroup(toCreate);
        ProductGroup result = repository.save(group);
        return toCreate;
    }

    @Override
    public ProductGroupDTO updateGroup(int id, ProductGroupDTO toUpdate) {
        getGroupById(id);
        toUpdate.setId(id);
        ProductGroup result = groupMapper.groupDtoToGroup(toUpdate);
        repository.save(result);
        return toUpdate;
    }

    @Override
    public ProductGroupDTO deleteProductById(int id) {
        ProductGroup result = getGroupById(id);
        repository.delete(result);
        return groupMapper.groupToGroupDto(result);
    }

    private ProductGroup getGroupById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, ProductGroup.class.getSimpleName()));
    }

}
