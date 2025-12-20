package br.com.fiap.baitersburger.products.adapters.out.mysql;

import br.com.fiap.baitersburger.products.adapters.out.mysql.repository.ProductRepository;
import br.com.fiap.baitersburger.products.core.domain.ports.out.DeleteProductOutputPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductAdapter implements DeleteProductOutputPort {
    private final ProductRepository repository;

    public DeleteProductAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
