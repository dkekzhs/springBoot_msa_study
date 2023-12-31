package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    @Override
    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("GlobalFilter Pre filter : baseMessage -> {}", config.getBaseMessage());
//            if(config.isPreLogger()){
//                log.info("GlobalFilter pre filter : request id -> {}",request.getId());
//            }
//            //custom Post Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
//                if(config.isPostLogger()){
//                    log.info("GlobalFilter Post filter : response status -> {}",response.getStatusCode());
//                }
//            }));
//        });
        GatewayFilter filter = new OrderedGatewayFilter(((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Pre filter : baseMessage -> {}", config.getBaseMessage());
            if(config.isPreLogger()){
                log.info("Logging pre filter : request id -> {}",request.getId());
            }
            //custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                if(config.isPostLogger()){
                    log.info("Logging Post filter : response status -> {}",response.getStatusCode());
                }
            }));
        }),Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }

    @Data
    public static class Config{
        //Put Configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
    public LoggingFilter(){
        super(Config.class);
    }

}
