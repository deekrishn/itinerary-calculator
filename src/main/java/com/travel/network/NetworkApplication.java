package com.travel.network;

import com.google.common.collect.ImmutableList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class NetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiDetails());

    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Flight Itinerary Calculator",
                "Given:\n " +
                        "1. City controller allowing you to\n a. Add a city. City object consisting of city name " +
                        "and country.\n b. Look at existing cities\n" +
                        "2. Flight controller allowing you to\n a. Look at existing flights\n " +
                        "b. Add a new flight. Flight object consisting of source city, destination city and the fare.\n " +
                        "c. Update a flight\n d. Delete a flight\n" +
                        "3. Itinerary controller allowing you to find the cheapest prices from source city to\n " +
                        "destination city within N number of stops",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Deepa Krishnan","https://github.com/deekrishn", "deekrishn@gmail.com"),
                "API License",
                "https://github.com/deekrishn",
                Collections.emptyList());
    }

}
