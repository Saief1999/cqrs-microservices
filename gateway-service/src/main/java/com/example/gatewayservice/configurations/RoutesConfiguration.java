package com.example.gatewayservice.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RoutesConfiguration {

    // lb : used to enable Netflix Ribbon load-balancing
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) throws java.net.URISyntaxException{
        return builder.routes()
                .route("commands_route", p ->
                        p.method(HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE)
                                .and()
                                .path("/commands/**")
                                .filters(f -> f.rewritePath("/commands/(?<segment>.*)","/${segment}")
                                        .circuitBreaker(c -> c.setName("breakMe").setFallbackUri("forward:/fallback/commands")))
                                .uri("lb://upsert-microservice/"))
                .route("queries_route", p ->
                        p.method(HttpMethod.GET)
                                .and()
                                .path("/queries/**")
                                .filters(f -> f.rewritePath("/queries/(?<segment>.*)","/${segment}")
                                        .circuitBreaker(c -> c.setName("breakMe").setFallbackUri("forward:/fallback/queries"))
                                )
                                .uri("lb://search-microservice/"))
//                .route("other",p->p.uri())
                .build();
    }
}
