package pl.slowikowski.demo.feign_client.soap_client.reader;

import com.raglis.library_api.soap.readers.GetAllReadersResponse;
import com.raglis.library_api.soap.readers.ObjectFactory;
import com.raglis.library_api.soap.readers.ReaderPortType;
import com.raglis.library_api.soap.readers.ReaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.crud.exception.WrongIdException;
import pl.slowikowski.demo.feign_client.crud.reader.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap_client.abstraction.CommonSoapClient;

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
        var request = objectFactory.createGetAllReadersRequest();
        request.setSearch(search);
        request.setPageable(mapper.toPageableXml(pageable));

        GetAllReadersResponse response = proxy.getAll(request);

        return mapper.toPageImpl(response, pageable);
    }

    @Override
    public ReaderDTO getById(final long id) {
        var request = objectFactory.createGetReaderRequest();
        request.setId(id);
        ReaderResponse response = proxy.getById(request);
        return mapper.toDto(response.getReader());
    }

    @Override
    public ReaderDTO save(final ReaderDTO dto) {
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
