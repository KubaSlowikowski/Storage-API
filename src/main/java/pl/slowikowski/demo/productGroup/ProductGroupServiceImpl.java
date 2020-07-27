package pl.slowikowski.demo.productGroup;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.abstraction.AbstractService;
import pl.slowikowski.demo.product.ProductMapper;
import pl.slowikowski.demo.product.ProductRepository;

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
        var entity = groupMapper.fromDto(dto);
        var savedResult = groupRepository.saveAndFlush(entity);
        entity.getProducts().forEach(productRepository::saveAndFlush);
        return groupMapper.toDto(savedResult);
    }

    @Override
    @Transactional
    public ProductGroupDTO update(int id, ProductGroupDTO toUpdate) {
        if (id == 1) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "This group is required for the website to function properly");
        }
        toUpdate.setId(id);

        var systemGroup = getEntityById(1);
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
    public ProductGroupDTO delete(int id) {
        if (id == 1) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "This group is required for the website to function properly");
        }
        var products = productMapper.toListDto(productRepository.findAllByGroup_Id(id)); //cause of ProductGroup --> @OneToMany CascadeType.MERGE
        products.forEach(productDTO -> {
            productDTO.setGroupId(1);
            productRepository.saveAndFlush(productMapper.fromDto(productDTO));
        });
        var dto = findById(id);
        groupRepository.deleteById(dto.getId());
        return dto;
    }
}
