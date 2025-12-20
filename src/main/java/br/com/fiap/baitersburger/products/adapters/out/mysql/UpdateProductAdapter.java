package br.com.fiap.baitersburger.products.adapters.out.mysql;

import br.com.fiap.baitersburger.products.adapters.out.mysql.mapper.ProductEntityMapper;
import br.com.fiap.baitersburger.products.adapters.out.mysql.repository.ProductRepository;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.UpdateProductOutputPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductAdapter implements UpdateProductOutputPort {
    private final ProductEntityMapper mapper;
    private final ProductRepository repository;

    public UpdateProductAdapter(ProductEntityMapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void update(Product product) {
        var entity = mapper.toProductEntity(product);
        repository.save(entity);
    }
}
