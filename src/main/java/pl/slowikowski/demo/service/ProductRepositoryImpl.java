package pl.slowikowski.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.model.ProductDTO;
import pl.slowikowski.demo.persistence.model.Product;
import pl.slowikowski.demo.persistence.repository.ProductGroupRepository;
import pl.slowikowski.demo.persistence.repository.ProductRepository;

import java.util.List;

public class ProductRepositoryImpl {

    private ProductRepository repository;
    private ProductGroupRepository groupRepository;

    public ProductRepositoryImpl(final ProductRepository repository, final ProductGroupRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAll(Pageable page) {
        return repository.findAll(page)
                .getContent();
    }

    public Product save(Product toCreate) {
        return repository.save(toCreate);
    }

    public ProductDTO findById(int id) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        return repository.findById(id).get().toDTO();
    }

    public void buyProduct(int id) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        repository.findById(id)
                .ifPresent(product -> {
                    product.toogle();
                    repository.save(product);
                });
    }

    public void updateProduct(int id, Product toUpdate) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        Product result = repository.findById(id).get();
        result.updateFrom(toUpdate);
        repository.save(result);
    }

    public List<Product> findAllByGroupId(int groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product group does not exist!");
        }
        return repository.findAllByGroup_Id(groupId);
    }
}