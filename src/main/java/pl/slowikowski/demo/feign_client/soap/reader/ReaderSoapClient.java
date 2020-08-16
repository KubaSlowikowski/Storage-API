package pl.slowikowski.demo.feign_client.soap.reader;

import com.raglis.library_api.soap.readers.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.crud.exception.WrongIdException;
import pl.slowikowski.demo.feign_client.crud.reader.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.CommonSoapClient;

@Component
class ReaderSoapClient implements CommonSoapClient<ReaderDTO> {

    private final ReaderPortType proxy;
    private final ObjectFactory objectFactory;
    private final ReaderWebMapper mapper;

    ReaderSoapClient(final ReaderPortType proxy, final ObjectFactory objectFactory, final ReaderWebMapper mapper) {
        this.proxy = proxy;
        this.objectFactory = objectFactory;
        this.mapper = mapper;
    }

    @Override
    public Page<ReaderDTO> findAll(final Pageable pageable, final String search) {
//        var request = objectFactory.createGetAllReadersRequest();
//        request.setSearch(search);
//        request.setPageable(mapper.toPageableXmlFromPageable(pageable));
//
//        GetAllResponse response = proxy.getAll(request);
//        var responsePage = response.getPage();
//        responsePage.getContent().forEach(xml -> );
//
//        var result = new PageImpl<>();

        return null;
    }

    @Override
    public ReaderDTO getById(final long id) {
        var request = objectFactory.createGetReaderRequest();
        request.setId(id);
        ReaderResponse response = proxy.getById(request);
        return mapper.toDto(response.getReader());
    }

    @Override
    public ReaderDTO add(final ReaderDTO dto) {
        var request = mapper.toCreateReaderRequestFromDto(dto);
        ReaderResponse response = proxy.create(request);
        return mapper.toDto(response.getReader());
    }

    @Override
    public ReaderDTO update(final ReaderDTO dto, final long id) {
        if (dto.getId() != id) {
            throw new WrongIdException();
        }
        var request = mapper.toUpdateReaderRequestFromDto(dto);
        ReaderResponse response = proxy.update(request);
        return mapper.toDto(response.getReader());
    }

    @Override
    public ReaderDTO delete(final long id) {
        var request = objectFactory.createDeleteReaderRequest();
        request.setId(id);
        ReaderResponse response = proxy.delete(request);
        return mapper.toDto(response.getReader());
    }
}
