package br.com.fiap.baitersburger.products.core.domain.ports.out;

import br.com.fiap.baitersburger.products.core.domain.model.Product;

public interface InsertProductOutputPort {
    void insert(Product product);
}
