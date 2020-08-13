package pl.slowikowski.demo.feign_client.soap.reader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.crud.reader.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap.abstraction.AbstractSoapClientController;

@RestController
@RequestMapping("/library/api/soap")
public class ReaderSoapController extends AbstractSoapClientController<ReaderDTO, ReaderSoapClient> {
    private final ReaderSoapClient client;

    public ReaderSoapController(ReaderSoapClient client) {
        super(client);
        this.client = client;
    }
}
