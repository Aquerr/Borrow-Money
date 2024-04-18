package io.github.aquerr.borrowmoney.gatewayservice.config;

import io.github.aquerr.borrowmoney.gatewayservice.filter.ApiExceptionGatewayFilter;
import io.github.aquerr.borrowmoney.gatewayservice.filter.AuthGatewayFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Slf4j
@Configuration
public class RoutesConfig
{
    @Bean
    public RouterFunction<ServerResponse> routes() {

        return route()
                .GET("/api/v1/configuration", http("/configuration-service/api/v1/configuration"))
                .POST("/api/v1/configuration", http("/configuration-service/api/v1/configuration"))
                .POST("/api/v1/auth", http("/authorization-service/api/v1/auth"))
                .POST("/api/v1/registration", http("/authorization-service/api/v1/registration"))
                .before(serverRequest -> {
                    log.info("Request: " + serverRequest.toString());
                    return serverRequest;
                })
                .after((serverRequest, serverResponse) -> {
                    log.info("Response: " + serverResponse.toString());
                    return serverResponse;
                })
                .filter(new ApiExceptionGatewayFilter())
                .filter(new AuthGatewayFilter())
                .build();
    }
}
