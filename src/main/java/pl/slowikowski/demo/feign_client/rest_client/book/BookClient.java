package pl.slowikowski.demo.feign_client.rest_client.book;

import org.springframework.cloud.openfeign.FeignClient;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.CommonLibraryClient;

@FeignClient(value = "book", url = "${library.url}/books")
public interface BookClient extends CommonLibraryClient<BookDTO> {
}
