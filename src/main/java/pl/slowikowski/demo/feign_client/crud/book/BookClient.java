package pl.slowikowski.demo.feign_client.crud.book;

import org.springframework.cloud.openfeign.FeignClient;
import pl.slowikowski.demo.feign_client.crud.abstraction.CommonLibraryClient;

@FeignClient(value = "book", url = "http://s0208:8082/books")
public interface BookClient extends CommonLibraryClient<BookDTO> {
}
