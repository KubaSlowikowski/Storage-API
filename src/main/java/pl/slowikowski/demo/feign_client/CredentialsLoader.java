package pl.slowikowski.demo.feign_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CredentialsLoader {

    static String username;
    static String password;

    private CredentialsLoader(final @Value("${feign.client.username}") String username,
                              final @Value("${feign.client.password}") String password) {
        CredentialsLoader.username = username;
        CredentialsLoader.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
