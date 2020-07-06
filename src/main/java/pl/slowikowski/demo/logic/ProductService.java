package pl.slowikowski.demo.logic;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import pl.slowikowski.demo.model.Product;
import pl.slowikowski.demo.model.ProductRepository;

public class ProductService {

    private ProductRepository repository;

    public ProductService(final ProductRepository repository) {
        this.repository = repository;
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
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND ,"Product does not exist!");
        }
        repository.findById(id)
                .ifPresent( product -> {
                            product.updateFrom(toUpdate);
                            repository.save(product);
                        }
                );
    }
}
