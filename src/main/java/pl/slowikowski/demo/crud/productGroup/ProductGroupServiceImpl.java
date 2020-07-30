package pl.slowikowski.demo.crud.productGroup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.crud.abstraction.AbstractService;
import pl.slowikowski.demo.crud.exception.GroupModifyingForbiddenException;
import pl.slowikowski.demo.crud.product.ProductMapper;
import pl.slowikowski.demo.crud.product.ProductRepository;

@Service
public class ProductGroupServiceImpl extends AbstractService<ProductGroup, ProductGroupDTO> implements ProductGroupService {

    private final ProductGroupRepository groupRepository;
    private final ProductRepository productRepository;
    private final ProductGroupMapper groupMapper;
    private final ProductMapper productMapper;

    public ProductGroupServiceImpl(final ProductGroupRepository groupRepository, final ProductRepository productRepository, final ProductGroupMapper groupMapper, final ProductMapper productMapper) {
        super(groupMapper, groupRepository);
        this.groupRepository = groupRepository;
        this.productRepository = productRepository;
        this.groupMapper = groupMapper;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductGroupDTO save(ProductGroupDTO dto) {
        if (dto.getId()!=null && dto.getId() == 1) {
            throw new GroupModifyingForbiddenException();
        }
        var entity = groupMapper.fromDto(dto);
        var savedResult = groupRepository.saveAndFlush(entity);
        if(entity.getProducts() != null) {
            entity.getProducts().forEach(productRepository::saveAndFlush);
        }
        return groupMapper.toDto(savedResult);
    }

    @Override
    @Transactional
    public ProductGroupDTO update(Long id, ProductGroupDTO toUpdate) {
        if (id == 1) {
            throw new GroupModifyingForbiddenException();
        }
        toUpdate.setId(id);

        var systemGroup = getEntityById(1L);
        var previousVersionOfGroup = getEntityById(id);

        previousVersionOfGroup
                .getProducts()
                .forEach(product -> product.setGroup(systemGroup));

        ProductGroup result = groupMapper.fromDto(toUpdate);
        groupRepository.save(result);
        return groupMapper.toDto(result);
    }

    @Override
    @Transactional
    public ProductGroupDTO delete(Long id) {
        if (id == 1) {
            throw new GroupModifyingForbiddenException();
        }
        productRepository.assignProductsFromGroupWithIdToSystemGroup(id);
        var dto = findById(id);
        groupRepository.deleteById(dto.getId());
        return dto;
    }
}
