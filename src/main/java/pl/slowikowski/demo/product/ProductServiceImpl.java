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

    private final ProductRepository repository;
    private final ProductGroupRepository groupRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(final ProductRepository repository, final ProductGroupRepository groupRepository, final ProductMapper productMapper) {
        super(productMapper, repository);
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllByGroupId(int groupId) {
        groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException(groupId, ProductGroup.class.getSimpleName()));
        List<Product> productList = repository.findAllByGroup_Id(groupId);
        return productMapper.toListDto(productList);
    }

    @Override
    @Transactional
    public ProductDTO buyProduct(int id) {
        ProductDTO productDto = findById(id);
        productDto.toogle();
        Product product = productMapper.fromDto(productDto);
        var result = repository.saveAndFlush(product);
        return productMapper.toDto(result);
    }
}
