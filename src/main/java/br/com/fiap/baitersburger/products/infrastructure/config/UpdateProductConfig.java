package br.com.fiap.baitersburger.products.infrastructure.config;

import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.application.usecase.UpdateProductUseCase;
import br.com.fiap.baitersburger.products.core.domain.ports.out.UpdateProductOutputPort;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateProductConfig {
    public UpdateProductUseCase updateProductUseCase(FindProductInputPort findProductInputPort,
                                                     UpdateProductOutputPort updateProductOutputPort) {
        return new UpdateProductUseCase(findProductInputPort, updateProductOutputPort);
    }
}
