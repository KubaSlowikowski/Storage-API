package pl.slowikowski.demo.feign_client.crud.reader;

import org.springframework.cloud.openfeign.FeignClient;
import pl.slowikowski.demo.feign_client.crud.abstraction.CommonLibraryClient;

@FeignClient(value = "reader", url = "http://s0208:8082/readers")
public interface ReaderClient extends CommonLibraryClient<ReaderDTO>  {
}
