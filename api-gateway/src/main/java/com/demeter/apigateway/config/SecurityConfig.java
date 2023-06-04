package com.demeter.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig{
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf().disable()
                .cors().and()
                .authorizeExchange(exchange ->
                        exchange
                                .pathMatchers("/eureka/**")
                                .permitAll()
//                                testeing angular
                                .pathMatchers("/recipes/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return serverHttpSecurity.build();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
