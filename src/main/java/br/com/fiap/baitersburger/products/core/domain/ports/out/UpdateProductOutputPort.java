package br.com.fiap.baitersburger.products.core.domain.ports.out;

import br.com.fiap.baitersburger.products.core.domain.model.Product;

public interface UpdateProductOutputPort {
    void update(Product product);
}
