package pl.slowikowski.demo.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

interface BaseRepository<T> {
    List<T> findAll();

    Page<T> findAll(Pageable page);

    Optional<T> findById(Integer id);

    T save(T entity);

    boolean existsById(Integer id);
}