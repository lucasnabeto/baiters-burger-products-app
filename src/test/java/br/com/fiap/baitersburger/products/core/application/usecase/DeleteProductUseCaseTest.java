package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.exception.ProductNotFoundException;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.DeleteProductOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductUseCaseTest {
    @Mock
    private FindProductInputPort findProductInputPort;

    @Mock
    private DeleteProductOutputPort deleteProductOutputPort;

    @InjectMocks
    private DeleteProductUseCase useCase;

    @Test
    void delete_shouldRemoveProduct() {
        Long id = 1L;
        Product product = mock(Product.class);
        when(findProductInputPort.findById(id)).thenReturn(product);

        useCase.delete(id);

        verify(findProductInputPort).findById(id);
        verify(deleteProductOutputPort).delete(id);
    }

    @Test
    void delete_shouldThrowProductNotFoundException() {
        Long id = 1L;
        when(findProductInputPort.findById(id)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> useCase.delete(id));
        verify(findProductInputPort).findById(id);
        verifyNoInteractions(deleteProductOutputPort);
    }
}