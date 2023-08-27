package com.demeter.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            exchange.getRequest().mutate()
                    .header("sub", getPrincipalSubFromToken())
                    .build();
            return chain.filter(exchange);
        });
    }

    private String getPrincipalSubFromToken(){
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaim("sub");
    }

    public static class Config {

    }
}
