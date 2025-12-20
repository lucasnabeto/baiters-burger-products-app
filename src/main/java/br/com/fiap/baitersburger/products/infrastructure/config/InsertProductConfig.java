package br.com.fiap.baitersburger.products.infrastructure.config;

import br.com.fiap.baitersburger.products.core.application.usecase.InsertProductUseCase;
import br.com.fiap.baitersburger.products.core.domain.ports.out.InsertProductOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertProductConfig {
    @Bean
    public InsertProductUseCase insertProductUseCase(InsertProductOutputPort insertProductOutputPort) {
        return new InsertProductUseCase(insertProductOutputPort);
    }
}
