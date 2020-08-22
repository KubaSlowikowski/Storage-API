package pl.slowikowski.demo.crud.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.crud.abstraction.AbstractService;
import pl.slowikowski.demo.crud.exception.NotFoundException;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;
import pl.slowikowski.demo.crud.productGroup.ProductGroupRepository;
import pl.slowikowski.demo.email.EmailService;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractService<Product, ProductDTO> implements ProductService {

    private final ProductRepository repository;
    private final ProductGroupRepository groupRepository;
    private final ProductMapper productMapper;
    private final EmailService emailService;

    public ProductServiceImpl(final ProductRepository repository, final ProductGroupRepository groupRepository, final ProductMapper productMapper, final EmailService emailService) {
        super(productMapper, repository, emailService);
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.productMapper = productMapper;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public Page<ProductDTO> findAllByGroupId(Long groupId, Pageable page) {
        groupRepository.findById(groupId).orElseThrow(() -> new NotFoundException(groupId, ProductGroup.class.getSimpleName()));
        List<Product> productList = repository.findAllByGroup_Id(groupId);
        return new PageImpl<>(productMapper.toListDto(productList), page, productList.size());
    }

    @Override
    @Transactional
    public ProductDTO buyProduct(Long id) {
        ProductDTO productDto = findById(id);
        productDto.toogle();
        Product product = productMapper.fromDto(productDto);
        var result = repository.saveAndFlush(product);
        return productMapper.toDto(result);
    }
}
