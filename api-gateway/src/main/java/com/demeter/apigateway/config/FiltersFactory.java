package com.demeter.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class FiltersFactory implements GatewayFilterFactory<FiltersFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new SubHeaderFilter();
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    public static class Config {}
}