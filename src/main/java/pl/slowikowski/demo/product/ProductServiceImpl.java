package pl.slowikowski.demo.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.abstraction.AbstractService;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductDTO> implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(final ProductRepository repository, ProductMapper productMapper) {
        super(productMapper, repository);
        this.repository = repository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllByGroupId(int groupId) {
        findById(groupId);
        List<Product> productList = repository.findAllByGroup_Id(groupId);
        return productMapper.toListDto(productList);
    }

    @Override
    @Transactional
    public ProductDTO buyProduct(int id) {
        ProductDTO productDto = findById(id);
        productDto.toogle();
        Product product = productMapper.fromDto(productDto);
        var result = repository.save(product);
        return productMapper.toDto(result);
    }
}
