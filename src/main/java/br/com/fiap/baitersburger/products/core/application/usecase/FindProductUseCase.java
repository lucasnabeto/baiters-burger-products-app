package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.exception.ProductNotFoundException;
import br.com.fiap.baitersburger.products.core.domain.model.Category;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.FindProductOutputPort;

import java.util.List;

public class FindProductUseCase implements FindProductInputPort {
    private final FindProductOutputPort findProductOutputPort;

    public FindProductUseCase(FindProductOutputPort findProductOutputPort) {
        this.findProductOutputPort = findProductOutputPort;
    }

    @Override
    public Product findById(Long id) {
        return findProductOutputPort.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return findProductOutputPort.findByCategory(Category.fromString(category));
    }
}
