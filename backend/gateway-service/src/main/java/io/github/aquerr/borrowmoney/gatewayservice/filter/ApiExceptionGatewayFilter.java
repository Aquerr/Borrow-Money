package io.github.aquerr.borrowmoney.gatewayservice.filter;

import io.github.aquerr.borrowmoney.gatewayservice.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public class ApiExceptionGatewayFilter implements HandlerFilterFunction<ServerResponse, ServerResponse>
{

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction next)
    {
        try
        {
            return next.handle(request);
        }
        catch (Exception exception)
        {
            //TODO: Make response better
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(defaultErrorResponse());
        }
    }

    private ApiErrorResponse defaultErrorResponse()
    {
        return new ApiErrorResponse("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
