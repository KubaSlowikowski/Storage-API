package pl.slowikowski.demo.feign_client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "library")
@Getter
@Setter
public class LibraryProperties {

    private String username;
    private String password;
    private String url;

}
