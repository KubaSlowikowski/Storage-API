package pl.slowikowski.demo.feign_client.authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth", url = "http://s0208:8082/auth")
public interface AuthLibraryClient {
    @PostMapping
    JwtResponse authenticateUser(@RequestBody AuthLibraryRequest request);
}
