package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.application.ports.in.InsertProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.InsertProductOutputPort;

public class InsertProductUseCase implements InsertProductInputPort {
    private final InsertProductOutputPort insertProductOutputPort;

    public InsertProductUseCase(InsertProductOutputPort insertProductOutputPort) {
        this.insertProductOutputPort = insertProductOutputPort;
    }

    @Override
    public void insert(Product product) {
        insertProductOutputPort.insert(product);
    }
}
