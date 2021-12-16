package NTTDATA.msproduct.service.Impl;

import NTTDATA.msproduct.model.Product;
import NTTDATA.msproduct.repository.ProductRepository;
import NTTDATA.msproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private  final ProductRepository repository;

    @Override
    public Mono<Product> save(Product product) {
        return repository.save(product);
    }

    @Override
    public Mono<Product> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Void> delete(Product product) {
        return repository.delete(product);
    }

    @Override
    public Mono<Product> findByProductName(String productName) {
        return repository.findByProductName(productName);
    }
}
