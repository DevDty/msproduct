package NTTDATA.msproduct.service;

import NTTDATA.msproduct.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    public Mono<Product> save(Product product);
    public Mono<Product> findById(String id);
    public Flux<Product> findAll();
    public Mono<Void> delete(Product product);
}
