package pl.slowikowski.demo.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends JpaRepository<E, Integer>, JpaSpecificationExecutor<E> {
}
