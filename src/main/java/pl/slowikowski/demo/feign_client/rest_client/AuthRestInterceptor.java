package pl.slowikowski.demo.feign_client.rest_client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryClient;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryRequest;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AuthRestInterceptor implements RequestInterceptor { //feign clients requests only
    private final AuthLibraryClient authClient;

    @Override
    public void apply(RequestTemplate template) {
        var authRequest = new AuthLibraryRequest();
        authRequest.setUsername("admin");
        authRequest.setPassword("adminadmin");
        var token = authClient.authenticateUser(authRequest).getToken();

        template.header("Authorization", "Bearer " + token);
    }
}
