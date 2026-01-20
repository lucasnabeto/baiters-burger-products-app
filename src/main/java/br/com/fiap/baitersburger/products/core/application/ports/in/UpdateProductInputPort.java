package br.com.fiap.baitersburger.products.core.application.ports.in;

import br.com.fiap.baitersburger.products.core.domain.model.Product;

public interface UpdateProductInputPort {
    void update(Product product);
}
