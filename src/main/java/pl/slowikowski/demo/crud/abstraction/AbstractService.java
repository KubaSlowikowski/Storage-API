package pl.slowikowski.demo.crud.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.crud.exception.NotFoundException;
import pl.slowikowski.demo.crud.exception.WrongIdException;

public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto> implements CommonService<D> {
    protected final CommonMapper<E, D> commonMapper;
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
    public D findById(Long id) {
        E entity = getEntityById(id);
        return commonMapper.toDto(entity);
    }

    @Transactional
    public E getEntityById(Long id) {
        return commonRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "entityName"));//FIXME
    }

    @Override
    @Transactional
    public D save(D dto) {
        E entity = commonMapper.fromDto(dto);
        E savedResult = commonRepository.saveAndFlush(entity);
        return commonMapper.toDto(savedResult);
    }

    @Override
    @Transactional
    public D update(Long id, D dto) {
        if(id < 1 || dto.getId() != id) {
            throw new WrongIdException(id);
        }
        E entity = commonMapper.fromDto(dto);
        commonRepository.saveAndFlush(entity);
        return dto;
    }

    @Override
    @Transactional
    public D delete(Long id) {
        E entity = getEntityById(id);
        commonRepository.deleteById(entity.getId());
        return commonMapper.toDto(entity);
    }
}
