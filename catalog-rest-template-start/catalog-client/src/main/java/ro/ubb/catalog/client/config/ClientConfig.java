package ro.ubb.catalog.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by beatrix.
 */
@Configuration
@ComponentScan({"ro.ubb.catalog.client.ui","ro.ubb.catalog.client.service" })
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
