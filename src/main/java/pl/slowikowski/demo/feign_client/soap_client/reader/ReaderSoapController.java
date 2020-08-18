package pl.slowikowski.demo.feign_client.soap_client.reader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.rest_client.reader.ReaderDTO;
import pl.slowikowski.demo.feign_client.soap_client.abstraction.AbstractSoapClientController;

@RestController
@RequestMapping("/library/api/soap/readers")
class ReaderSoapController extends AbstractSoapClientController<ReaderDTO, ReaderSoapClient> {

    private final ReaderSoapClient client;

    ReaderSoapController(final ReaderSoapClient client) {
        super(client);
        this.client = client;
    }
}