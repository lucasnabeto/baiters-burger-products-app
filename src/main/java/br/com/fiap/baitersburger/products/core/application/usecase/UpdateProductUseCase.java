package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.UpdateProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.UpdateProductOutputPort;

public class UpdateProductUseCase implements UpdateProductInputPort {
    private final FindProductInputPort findProductInputPort;
    private final UpdateProductOutputPort updateProductOutputPort;

    public UpdateProductUseCase(FindProductInputPort findProductInputPort, UpdateProductOutputPort updateProductOutputPort) {
        this.findProductInputPort = findProductInputPort;
        this.updateProductOutputPort = updateProductOutputPort;
    }

    @Override
    public void update(Product product) {
        findProductInputPort.findById(product.getId());
        updateProductOutputPort.update(product);
    }
}
