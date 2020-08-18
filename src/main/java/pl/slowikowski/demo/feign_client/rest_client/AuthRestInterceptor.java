package pl.slowikowski.demo.feign_client.rest_client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import pl.slowikowski.demo.feign_client.LibraryProperties;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryClient;
import pl.slowikowski.demo.feign_client.authorization.AuthLibraryRequest;

@Configuration
@Slf4j
public class AuthRestInterceptor implements RequestInterceptor { //feign clients requests only
    private final String username;
    private final String password;
    private final AuthLibraryClient authClient;
    private LibraryProperties libraryProperties;

    public AuthRestInterceptor(final AuthLibraryClient authClient, LibraryProperties libraryProperties) {
        this.authClient = authClient;
        this.username = libraryProperties.getUsername();
        this.password = libraryProperties.getPassword();
    }

    @Override
    public void apply(RequestTemplate template) {
        var authRequest = new AuthLibraryRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        var token = authClient.authenticateUser(authRequest).getToken();

        template.header("Authorization", "Bearer " + token);
    }
}
