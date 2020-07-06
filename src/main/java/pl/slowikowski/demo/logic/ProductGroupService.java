package pl.slowikowski.demo.logic;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.model.ProductGroup;
import pl.slowikowski.demo.model.ProductGroupRepository;

public class ProductGroupService {

    private ProductGroupRepository repository;

    public ProductGroupService(final ProductGroupRepository repository) {
        this.repository = repository;
    }

    public void updateGroup(int id, ProductGroup toUpdate) {
        if (!repository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product group does not exist!");
        }
        repository.findById(id)
                .ifPresent(group -> {
                            group.updateFrom(toUpdate);
                            repository.save(group);
                        }
                );
    }
}