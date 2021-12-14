package NTTDATA.msproduct.config;

import NTTDATA.msproduct.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ProductHandler handler){
        return route(GET("/product/{id}"), handler::findById)
                .andRoute(POST("/product"), handler::create)
                .andRoute(GET("/product"), handler::findAll)
                .andRoute(PUT("/product/{id}"), handler::update)
                .andRoute(DELETE("/product/{id}"), handler::delete);

    }
}
