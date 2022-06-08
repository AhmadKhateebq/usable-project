package com.example.redirectserver;

import com.example.redirectserver.util.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RedirectServerApplication {

    public static void main(String[] args) {
        SpringApplication.run (RedirectServerApplication.class, args);
    }

    @Bean
    RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate ();
        restTemplate.setRequestFactory(new Client.CustomHttpComponentsClientHttpRequestFactory ());
        return restTemplate;
    }
    @Bean
    Client getClient(){
        return new Client ();
    }

}
