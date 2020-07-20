package pl.slowikowski.demo.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.exception.NotFoundException;

public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto> implements CommonService<D> {
    protected final CommonMapper<E,D> commonMapper;
    protected final CommonRepository<E> commonRepository;

    public AbstractService(CommonMapper<E, D> commonMapper, CommonRepository<E> commonRepository) {
        this.commonMapper = commonMapper;
        this.commonRepository = commonRepository;
    }

    @Override
    @Transactional
    public Page<D> getAll(Pageable page) {
        var result = commonRepository.findAll(page);
        var content = commonMapper.toListDto(result.getContent());
        return new PageImpl<>(content, page, result.getTotalElements());
    }

    @Override
    @Transactional
    public D findById(int id) {
        E entity = getEntityById(id);
        return commonMapper.toDto(entity);
    }

    private E getEntityById(int id) {
        return commonRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "entity"));
    }

    @Override
    @Transactional
    public D save(D dto) {
        E entity = commonMapper.fromDto(dto);
        E savedResult = commonRepository.save(entity);
        return commonMapper.toDto(savedResult);
    }

    @Override
    @Transactional
    public D update(int id, D dto) {
        dto.setId(id);
        E entity = commonMapper.fromDto(dto);
        commonRepository.save(entity);
        return dto;
    }

    @Override
    @Transactional
    public D delete(int id) {
        E entity = getEntityById(id);
        commonRepository.delete(entity);
        return commonMapper.toDto(entity);
    }
}
