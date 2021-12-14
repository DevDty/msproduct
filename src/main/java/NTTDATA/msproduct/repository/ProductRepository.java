package NTTDATA.msproduct.repository;

import NTTDATA.msproduct.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository  extends ReactiveMongoRepository<Product, String> {
    Mono<Product> save(Product product);
    Mono<Product> findById(String id);
    Flux<Product> findAll();
    Mono<Void> delete(Product product);
}
