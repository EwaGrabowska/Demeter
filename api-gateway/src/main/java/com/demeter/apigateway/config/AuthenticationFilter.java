package com.demeter.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
//            exchange.getRequest().mutate()
//                    .header("token", )
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
