package pl.slowikowski.demo.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.slowikowski.demo.persistence.model.Product;

import java.util.*;

public class TestProductRepository implements ProductRepository {

    private Map<Integer, Product> products = new HashMap<>();

    @Override
    public List<Product> findAllByGroup_Id(Integer groupId) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Page<Product> findAll(Pageable page) {
        return null;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Product save(Product entity) {
        return products.put(products.size() + 1, entity);
    }

    @Override
    public boolean existsById(Integer id) {
        return products.containsKey(id);
    }
}