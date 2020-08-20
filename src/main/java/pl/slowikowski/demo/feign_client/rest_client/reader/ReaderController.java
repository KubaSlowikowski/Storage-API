package pl.slowikowski.demo.feign_client.rest_client.reader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.AbstractLibraryController;

@RestController
@RequestMapping("${storage.library.readers.url}")
class ReaderController extends AbstractLibraryController<ReaderDTO, ReaderClient> {
    private final ReaderClient client;

    public ReaderController(final ReaderClient client, final ReaderClientService service) {
        super(client, service);
        this.client = client;
    }
}
