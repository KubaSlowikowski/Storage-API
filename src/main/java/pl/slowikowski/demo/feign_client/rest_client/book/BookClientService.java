package pl.slowikowski.demo.feign_client.rest_client.book;

import org.springframework.stereotype.Service;
import pl.slowikowski.demo.feign_client.dto.BookDTO;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.AbstractLibraryClientService;

@Service
public class BookClientService extends AbstractLibraryClientService<BookDTO, BookClient> {
    protected BookClientService(BookClient client) {
        super(client);
    }
}
