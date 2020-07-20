package pl.slowikowski.demo.productGroup;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.abstraction.AbstractService;
import pl.slowikowski.demo.exception.NotFoundException;
import pl.slowikowski.demo.product.Product;
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
    public ProductGroupDTO save(ProductGroupDTO toCreate) {
        var productsDto = toCreate.getProducts();
        toCreate.setProducts(null);
        var group = groupMapper.fromDto(toCreate);
        ProductGroup result = groupRepository.saveAndFlush(group);
//after mapping TODO
        var products = productMapper.fromSetDto(productsDto);
        if (products != null) {
            products.forEach(product -> {
                product.setGroup(result);
                productRepository.save(product);
            });
        }
        result.setProducts(products);
        groupRepository.save(result);

        return groupMapper.toDto(result);
    }

    @Override
    @Transactional
    public ProductGroupDTO update(int id, ProductGroupDTO toUpdate) {
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
            //modifying
            var newProducts = toUpdate.getProducts();
            for (Product product : getGroupById(id).getProducts()) {
                if (!newProducts.contains(productMapper.toDto(product))) { //will move orphan products to default group
                    product.setGroup(getGroupById(1));
                    productRepository.save(product);
                }
            }
            toUpdate.getProducts().forEach(productDTO -> productDTO.setGroupId(id));
        }

        ProductGroup result = groupMapper.fromDto(toUpdate);
        groupRepository.save(result);
        return toUpdate;
    }

    @Transactional
    public ProductGroupDTO delete(int id) {
        if (id == 1) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "This group is required for the website to function properly");
        }
        ProductGroup result = getGroupById(id);
        groupRepository.delete(result);
        return groupMapper.toDto(result);
    }

    private ProductGroup getGroupById(int id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, ProductGroup.class.getSimpleName()));
    }
}
