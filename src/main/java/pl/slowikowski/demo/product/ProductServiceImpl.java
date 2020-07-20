package pl.slowikowski.demo.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.abstraction.AbstractService;
import pl.slowikowski.demo.exception.NotFoundException;
import pl.slowikowski.demo.productGroup.ProductGroup;
import pl.slowikowski.demo.productGroup.ProductGroupRepository;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductDTO> implements ProductService {

    private final  ProductRepository repository;
    private final ProductGroupRepository groupRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(final ProductRepository repository, final ProductGroupRepository groupRepository, ProductMapper productMapper) {
        super(productMapper, repository);
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllByGroupId(int groupId) {
        getGroupById(groupId);
        List<Product> productList = repository.findAllByGroup_Id(groupId);
        return productMapper.toListDto(productList);
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO toCreate) {
        if (toCreate.getGroupId() == 0) {
            toCreate.setGroupId(1);
        }
        Product product = productMapper.fromDto(toCreate);
        Product result = repository.save(product);
        return productMapper.toDto(result);
    }

    @Override
    @Transactional
    public ProductDTO update(int id, ProductDTO toUpdate) {
        if (toUpdate.getGroupId() == 0) {
            toUpdate.setGroupId(1);
        }
        toUpdate.setId(id);
        Product result = productMapper.fromDto(toUpdate);
        repository.save(result);
        return toUpdate;
    }

    @Override
    @Transactional
    public ProductDTO buyProduct(int id) {
        Product product = getProductById(id);
        ProductDTO productDto = productMapper.toDto(product);
        productDto.toogle();
        product = productMapper.fromDto(productDto);
        repository.save(product);
        return productDto;
    }

    private Product getProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, Product.class.getSimpleName()));
    }

    private ProductGroup getGroupById(int id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, ProductGroup.class.getSimpleName()));
    }
}
