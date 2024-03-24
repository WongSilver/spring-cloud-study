package edu.wong.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;


// 设置一个标志位, 匹配上才能访问
@Slf4j
@Component
public class CustomSingleGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomSingleGatewayFilterFactory.Config> {

    public CustomSingleGatewayFilterFactory() {
        super(CustomSingleGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(CustomSingleGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            log.info("进入单一的过滤器 status: {}", config.getStatus());
            ServerHttpRequest request = exchange.getRequest();
            if (request.getQueryParams().containsKey(config.getStatus())) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("status");
    }

    @Getter
    @Setter
    public static class Config {
        private String status;
    }

}
