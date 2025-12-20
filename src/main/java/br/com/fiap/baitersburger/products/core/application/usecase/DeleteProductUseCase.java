package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.application.ports.in.DeleteProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.ports.out.DeleteProductOutputPort;

public class DeleteProductUseCase implements DeleteProductInputPort {
    private final FindProductInputPort findProductByIdInputPort;
    private final DeleteProductOutputPort deleteProductOutputPort;

    public DeleteProductUseCase(FindProductInputPort findProductByIdInputPort, DeleteProductOutputPort deleteProductOutputPort) {
        this.findProductByIdInputPort = findProductByIdInputPort;
        this.deleteProductOutputPort = deleteProductOutputPort;
    }

    @Override
    public void delete(Long id) {
        findProductByIdInputPort.findById(id);
        deleteProductOutputPort.delete(id);
    }
}
