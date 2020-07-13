package pl.slowikowski.demo.service.impl;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.entity.Product;
import pl.slowikowski.demo.entity.ProductGroup;
import pl.slowikowski.demo.exception.NotFoundException;
import pl.slowikowski.demo.mapper.ProductMapper;
import pl.slowikowski.demo.model.ProductDTO;
import pl.slowikowski.demo.repository.ProductGroupRepository;
import pl.slowikowski.demo.repository.ProductRepository;
import pl.slowikowski.demo.service.ProductService;

import java.util.List;


public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ProductGroupRepository groupRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(final ProductRepository repository, final ProductGroupRepository groupRepository, ProductMapper productMapper) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> findAllProducts(Pageable page) {
        List<Product> result = repository.findAll(page).getContent();
        return productMapper.productsListToProductDtoList(result);
    }

    @Override
    public ProductDTO findById(int id) {
        Product result = getProductById(id);
        return productMapper.productToProductDto(result);
    }

    @Override
    public List<ProductDTO> findAllByGroupId(int groupId) {
        getGroupById(groupId);
        List<Product> productList = repository.findAllByGroup_Id(groupId);
        return productMapper.productsListToProductDtoList(productList);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO toCreate) {
        Product product = productMapper.productDtoToProduct(toCreate);
        Product result = repository.save(product);
        return productMapper.productToProductDto(result);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO toUpdate) {
        getProductById(id);
        toUpdate.setId(id);
        Product result = productMapper.productDtoToProduct(toUpdate);
        repository.save(result);
        return toUpdate;
    }

    @Override
    public ProductDTO deleteProductById(int id) {
        Product result = getProductById(id);
        repository.delete(result);
        return productMapper.productToProductDto(result);
    }

    @Override
    public ProductDTO buyProduct(int id) {
        Product product = getProductById(id);
        ProductDTO productDto = productMapper.productToProductDto(product);
        productDto.toogle();
        product = productMapper.productDtoToProduct(productDto);
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
