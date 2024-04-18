package io.github.aquerr.borrowmoney.gatewayservice.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public class AuthGatewayFilter implements HandlerFilterFunction<ServerResponse, ServerResponse>
{

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception
    {
        String authorizationHeader = request.headers().firstHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null)
            throw new IllegalStateException("No access!");

        //TODO: Get token and validate it

        //TODO: To validate token, contact authorization-service (or name it better)
        //TODO: It would be worth to have some kind of cache...

        return next.handle(request);
    }
}
