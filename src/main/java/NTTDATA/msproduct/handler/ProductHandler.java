package NTTDATA.msproduct.handler;

import NTTDATA.msproduct.model.Product;
import NTTDATA.msproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.net.URI;

@Component
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductService service;

    public Mono<ServerResponse> create (ServerRequest request){
        Mono<Product> productMono = request.bodyToMono(Product.class);
        return productMono
                .flatMap(service::save)
                .flatMap(r -> ServerResponse.created(URI.create("/product/".concat(r.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(r)
                );
    }

    public Mono<ServerResponse> findById (ServerRequest request){
        String id = request.pathVariable("id");
        return service.findById(id)
                .flatMap(r -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(r)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByProductName(ServerRequest request){
        String productName = request.pathVariable("productName");
        return service.findByProductName(productName)
                .flatMap(r -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(r)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findAll (ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Product.class);
    }

    public Mono<ServerResponse> update (ServerRequest request){
        Mono<Product> productMono = request.bodyToMono(Product.class);
        String id = request.pathVariable("id");
        Mono<Product> productMonoDb = service.findById(id);
        return productMonoDb.zipWith(productMono, (db, req) ->{
                    db.setProductName(req.getProductName());
                    db.setCurrency(req.getCurrency());
                    db.setProductType(req.getProductType());
                    db.setProductRule(req.getProductRule());
                    return db;
                }).flatMap(p -> ServerResponse.created(URI.create("/product/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.save(p), Product.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete (ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Product> productMonoDb = service.findById(id);
        return productMonoDb.flatMap(p -> service.delete(p).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
