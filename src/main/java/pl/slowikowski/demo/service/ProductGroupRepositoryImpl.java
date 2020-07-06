package pl.slowikowski.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.model.ProductGroupDTO;
import pl.slowikowski.demo.persistence.model.ProductGroup;
import pl.slowikowski.demo.persistence.repository.ProductGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductGroupRepositoryImpl {

    private ProductGroupRepository repository;

    public ProductGroupRepositoryImpl(final ProductGroupRepository repository) {
        this.repository = repository;
    }

    public List<ProductGroup> findAll() {
        return repository.findAll();
    }

    public List<ProductGroup> findAll(Pageable page) {
        return repository.findAll(page).getContent();
    }

    public ProductGroup save(ProductGroup toCreate) {
        return repository.save(toCreate);
    }

    public ProductGroupDTO findById(int id) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        return repository.findById(id).get().toDTO();
    }

    public void updateGroup(int id, ProductGroup toUpdate) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product group does not exist!");
        }
        ProductGroup result = repository.findById(id).get();
        result.updateFrom(toUpdate);
        repository.save(result);
    }
}