package br.com.fiap.baitersburger.products.core.application.ports.in;

import br.com.fiap.baitersburger.products.core.domain.model.Product;

import java.util.List;

public interface FindProductInputPort {
    Product findById(Long id);

    List<Product> findByCategory(String category);
}
