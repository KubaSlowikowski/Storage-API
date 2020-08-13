package pl.slowikowski.demo.feign_client.soap.reader;

import com.raglis.library_api.soap._abstract.GetAllResponse;
import com.raglis.library_api.soap.readers.ObjectFactory;
import com.raglis.library_api.soap.readers.ReaderPortType;
import com.raglis.library_api.soap.readers.ReaderXml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.slowikowski.demo.feign_client.crud.reader.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap.PageMapper;
import pl.slowikowski.demo.feign_client.soap.abstraction.CommonSoapClient;

@Component
public class ReaderSoapClient implements CommonSoapClient<ReaderDTO> {

    private final ReaderPortType proxy;
    private final ObjectFactory objectFactory;
    private final PageMapper<ReaderXml, ReaderDTO> mapper;

    public ReaderSoapClient(final ReaderPortType proxy, final ObjectFactory objectFactory, final PageMapper<ReaderXml, ReaderDTO> mapper) {
        this.proxy = proxy;
        this.objectFactory = objectFactory;
        this.mapper = mapper;
    }

    @Override
    public Page<ReaderDTO> findAll(Pageable pageable, String search) {
        var request = objectFactory.createGetAllReadersRequest();
        request.setSearch(search);
        request.setPageable(mapper.toPageableXmlFromPageable(pageable));

        GetAllResponse response = proxy.getAll(request);
        var responsePage = response.getPage();

        var result = new PageImpl<>();

        return null;
    }

    @Override
    public ReaderDTO getById(long id) {
        return null;
    }

    @Override
    public ReaderDTO add(ReaderDTO dto) {
        return null;
    }

    @Override
    public ReaderDTO update(ReaderDTO dto, long id) {
        return null;
    }

    @Override
    public ReaderDTO delete(long id) {
        return null;
    }


}
