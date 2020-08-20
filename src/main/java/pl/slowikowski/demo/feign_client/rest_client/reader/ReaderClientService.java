package pl.slowikowski.demo.feign_client.rest_client.reader;

import org.springframework.stereotype.Service;
import pl.slowikowski.demo.feign_client.dto.ReaderDTO;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.AbstractLibraryClientService;

@Service
public class ReaderClientService extends AbstractLibraryClientService<ReaderDTO, ReaderClient> {
    protected ReaderClientService(ReaderClient client) {
        super(client);
    }
}
