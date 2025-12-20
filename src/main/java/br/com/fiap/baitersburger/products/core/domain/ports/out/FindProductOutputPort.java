package br.com.fiap.baitersburger.products.core.domain.ports.out;

import br.com.fiap.baitersburger.products.core.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface FindProductOutputPort {
    Optional<Product> findById(Long id);

    List<Product> findByCategory(String category);
}
