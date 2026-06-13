package com.relatospapel.msgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class GatewayLoggingFilterConfig {

    @Bean
    public GlobalFilter logRouteFilter() {
        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            var request = exchange.getRequest();

            if (route != null) {
                log.info("GW -> routeId={}, method={}, path={}, targetUri={}",
                        route.getId(),
                        request.getMethod(),
                        request.getURI().getPath(),
                        route.getUri());
            } else {
                log.info("GW -> NO_ROUTE method={}, path={}",
                        request.getMethod(),
                        request.getURI().getPath());
            }

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() ->
                            log.info("GW <- status={}, path={}",
                                    exchange.getResponse().getStatusCode(),
                                    request.getURI().getPath()
                            )));
        };
    }
}
