package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator gatewatRoutes(RouteLocatorBuilder builder){
        return builder.routes().route(r ->
                r.path("/first-service/**").filters(
                        f -> f.addRequestHeader("first-req","first-req")
                                .addResponseHeader("first-res","first-res")
                ).uri("http://localhost:8081")
        ).route(r ->
                        r.path("/second-service/**").filters(
                                f -> f.addRequestHeader("second-req","second-req")
                                        .addResponseHeader("second-res","second-res")
                        ).uri("http://localhost:8082")
                )
                .build();
    }

}
