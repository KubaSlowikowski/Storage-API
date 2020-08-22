package pl.slowikowski.demo.crud.abstraction;

import com.google.common.base.Joiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import pl.slowikowski.demo.crud.exception.NotFoundException;
import pl.slowikowski.demo.crud.exception.WrongIdException;
import pl.slowikowski.demo.crud.searchSpecification.CommonSearchSpecificationBuilder;
import pl.slowikowski.demo.crud.searchSpecification.SearchOperation;
import pl.slowikowski.demo.email.EmailService;
import pl.slowikowski.demo.email.Message;
import pl.slowikowski.demo.export.ExportDto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto> implements CommonService<D>, EmailSender {
    private final CommonMapper<E, D> commonMapper;
    private final CommonRepository<E> commonRepository;
    private final EmailService emailService;

    public AbstractService(final CommonMapper<E, D> commonMapper, final CommonRepository<E> commonRepository, final EmailService emailService) {
        this.commonMapper = commonMapper;
        this.commonRepository = commonRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public Page<D> getAll(Pageable pageable, String search) {
        Specification<E> spec = resolveSpecification(search);
        Page<E> result = commonRepository.findAll(spec, pageable);
        List<D> content = commonMapper.toListDto(result.getContent());
        return new PageImpl<>(content, pageable, result.getTotalElements());
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
        if (id < 1 || dto.getId() != id) {
            throw new WrongIdException(id);
        }
        getEntityById(id);
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

    @Override
    @Transactional
    public ExportDto getAllInFile(Pageable pageable, String search, String fileExtension) {
        List<D> dtos = getAll(pageable, search).getContent();
        return toPdfReport(dtos, fileExtension);
    }

    @Override
    @Transactional
//    @Async
    public void sendAllInMail(final Pageable pageable, final String search, final Message message) {
        ExportDto file = getAllInFile(pageable, search, message.getFileExtension());
        message.setFile(file);
        emailService.sendMessageWithAttachment(message);
    }

    private Specification<E> resolveSpecification(String searchParameters) {
        CommonSearchSpecificationBuilder<E> builder = new CommonSearchSpecificationBuilder<>();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\p{Punct}?)(\\w+?)("
                        + operationSetExper
                        + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchParameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3),
                    matcher.group(5), matcher.group(4), matcher.group(6));
        }

        return builder.build();
    }
}
