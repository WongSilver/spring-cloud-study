package edu.wong.gateway;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/** 自定义会员等级
 * @Author WongSilver
 * @Date 2024/3/20 14:47
 * @Description
 */
@Component
public class CustomRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomRoutePredicateFactory.Config> {


    public CustomRoutePredicateFactory() {
        super(CustomRoutePredicateFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userLevel");
    }

    @Override
    public Predicate<ServerWebExchange> apply(CustomRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            String userLevel = serverWebExchange.getRequest().getQueryParams().getFirst("userLevel");
            if (userLevel == null) {
                return false;
            }
            return userLevel.equalsIgnoreCase(config.getUserLevel());
        };
    }

    @Getter
    @Setter
    @Validated
    public static class Config {
        @NotNull
        private String userLevel;
    }

}
