package br.com.fiap.baitersburger.products.infrastructure.config;

import br.com.fiap.baitersburger.products.core.application.usecase.FindProductUseCase;
import br.com.fiap.baitersburger.products.core.domain.ports.out.FindProductOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindProductConfig {
    @Bean
    public FindProductUseCase findProductUseCase(FindProductOutputPort findProductOutputPort) {
        return new FindProductUseCase(findProductOutputPort);
    }
}
