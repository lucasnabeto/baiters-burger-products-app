package br.com.fiap.baitersburger.products.adapters.out.mysql;

import br.com.fiap.baitersburger.products.adapters.out.mysql.mapper.ProductEntityMapper;
import br.com.fiap.baitersburger.products.adapters.out.mysql.repository.ProductRepository;
import br.com.fiap.baitersburger.products.core.domain.model.Category;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.FindProductOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindProductAdapter implements FindProductOutputPort {
    private final ProductEntityMapper mapper;
    private final ProductRepository repository;

    public FindProductAdapter(ProductEntityMapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        var entity = repository.findById(id);
        return entity.map(mapper::toProduct);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category)
                .stream()
                .map(mapper::toProduct)
                .toList();
    }
}
