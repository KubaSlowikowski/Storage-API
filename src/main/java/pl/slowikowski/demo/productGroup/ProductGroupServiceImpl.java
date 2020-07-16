package pl.slowikowski.demo.productGroup;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.exception.NotFoundException;
import pl.slowikowski.demo.product.Product;
import pl.slowikowski.demo.product.ProductMapper;
import pl.slowikowski.demo.product.ProductRepository;

import java.util.List;

@Service
public class ProductGroupServiceImpl implements ProductGroupService {

    private ProductGroupRepository repository;
    private ProductRepository productRepository;
    private final ProductGroupMapper groupMapper;
    private final ProductMapper productMapper;

    public ProductGroupServiceImpl(final ProductGroupRepository repository, final ProductRepository productRepository, final ProductGroupMapper groupMapper, final ProductMapper productMapper) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.groupMapper = groupMapper;
        this.productMapper = productMapper;
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
        toUpdate.setId(id);

        if (toUpdate.getProducts() == null) {
            getGroupById(id) // group from database before update
                    .getProducts()
                    .stream()
                    .forEach(product -> {
                        product.setGroup(getGroupById(1));
                        productRepository.save(product); //will move all products to default group
                    });
        } else {
            var newProducts = toUpdate.getProducts();
            for (Product product : getGroupById(id).getProducts()) {
                if (!newProducts.contains(productMapper.productToProductDto(product))) { //will move orphan products to default group
                    product.setGroup(getGroupById(1));
                    productRepository.save(product);
                }
            }
            toUpdate.getProducts().forEach(productDTO -> productDTO.setGroupId(id));
        }

        ProductGroup result = groupMapper.groupDtoToGroup(toUpdate);
        result = updateCreationData(result, getGroupById(id));
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

    private ProductGroup updateCreationData(ProductGroup productGroup, ProductGroup source) {
        var updatedProducts = productGroup.getProducts();
        updatedProducts.forEach(product -> {
            var productFromRepo = productRepository.findById(product.getId()).get();
            product.setCreatedOn(productFromRepo.getCreatedOn());
            product.setCreatedBy(productFromRepo.getCreatedBy());
        });
        productGroup.setProducts(updatedProducts);

        productGroup.setCreatedBy(source.getCreatedBy());
        productGroup.setCreatedOn(source.getCreatedOn());
        return productGroup;
    }
}
