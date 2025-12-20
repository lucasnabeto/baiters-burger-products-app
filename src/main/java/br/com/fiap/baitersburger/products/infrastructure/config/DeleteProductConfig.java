package br.com.fiap.baitersburger.products.infrastructure.config;

import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.application.usecase.DeleteProductUseCase;
import br.com.fiap.baitersburger.products.core.domain.ports.out.DeleteProductOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteProductConfig {
    @Bean
    public DeleteProductUseCase deleteProductUseCase(FindProductInputPort findProductByIdInputPort,
                                                     DeleteProductOutputPort deleteProductOutputPort) {
        return new DeleteProductUseCase(findProductByIdInputPort, deleteProductOutputPort);
    }
}
