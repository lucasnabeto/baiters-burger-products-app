package br.com.fiap.baitersburger.products.core.application.ports.in;

import br.com.fiap.baitersburger.products.core.domain.model.Product;

public interface InsertProductInputPort {
    void insert(Product product);
}
