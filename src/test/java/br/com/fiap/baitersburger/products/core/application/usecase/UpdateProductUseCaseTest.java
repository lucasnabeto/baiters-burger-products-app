package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.UpdateProductOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateProductUseCaseTest {
    @Mock
    private FindProductInputPort findProductInputPort;

    @Mock
    private UpdateProductOutputPort updateProductOutputPort;

    @InjectMocks
    private UpdateProductUseCase useCase;

    @Test
    void update_shouldUpdateProduct() {
        Product product = mock(Product.class);

        useCase.update(product);

        verify(updateProductOutputPort).update(product);
    }
}