package pl.slowikowski.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.slowikowski.demo.persistence.repository.ProductRepository;
import pl.slowikowski.demo.persistence.repository.TestProductRepository;

@Configuration
class TestConfiguration {

    @Bean
    @Primary //ten bean przejmuje kontrolę, gdy odpalamy wszystko w profilu "integration"
    @Profile("integration") //to repo bedzie dzialalo, jeśli odpalimy Springa na profilu 'integration'
    ProductRepository testRepo() {
        return new TestProductRepository();
    }
}