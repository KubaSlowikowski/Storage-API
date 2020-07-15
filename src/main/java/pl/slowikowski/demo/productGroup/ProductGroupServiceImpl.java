package pl.slowikowski.demo.productGroup;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.exception.NotFoundException;

import java.util.List;

@Service
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
        return groupMapper.groupToGroupDto(result);
    }

    @Override
    public ProductGroupDTO updateGroup(int id, ProductGroupDTO toUpdate) {
        if (id == 1) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "This group is required for the website to function properly");
        }
        getGroupById(id);
        toUpdate.setId(id);
        ProductGroup result = groupMapper.groupDtoToGroup(toUpdate);
        repository.save(result);
        return toUpdate;
    }

    @Override
    public ProductGroupDTO deleteProductById(int id) {
        if (id == 1) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "This group is required for the website to function properly");
        }
        ProductGroup result = getGroupById(id);
        repository.delete(result);
        return groupMapper.groupToGroupDto(result);
    }

    private ProductGroup getGroupById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, ProductGroup.class.getSimpleName()));
    }

}
