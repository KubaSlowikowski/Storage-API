package pl.slowikowski.demo.feign_client.rest_client.reader;

import org.springframework.cloud.openfeign.FeignClient;
import pl.slowikowski.demo.feign_client.rest_client.abstraction.CommonLibraryClient;

@FeignClient(value = "reader", url = "${library.url}/readers")
public interface ReaderClient extends CommonLibraryClient<ReaderDTO>  {
}