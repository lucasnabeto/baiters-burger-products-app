package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.InsertProductOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsertProductUseCaseTest {
    @Mock
    private InsertProductOutputPort insertProductOutputPort;

    @InjectMocks
    private InsertProductUseCase useCase;

    @Test
    void insert_shouldCreateProduct() {
        Product product = mock(Product.class);

        useCase.insert(product);

        verify(insertProductOutputPort).insert(product);
    }
}