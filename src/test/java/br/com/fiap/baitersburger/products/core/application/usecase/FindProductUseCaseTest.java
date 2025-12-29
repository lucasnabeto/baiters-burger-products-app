package br.com.fiap.baitersburger.products.core.application.usecase;

import br.com.fiap.baitersburger.products.core.domain.exception.ProductNotFoundException;
import br.com.fiap.baitersburger.products.core.domain.model.Category;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import br.com.fiap.baitersburger.products.core.domain.ports.out.FindProductOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindProductUseCaseTest {
    @Mock
    private FindProductOutputPort findProductOutputPort;

    @InjectMocks
    private FindProductUseCase useCase;

    @Test
    void findById_shouldReturnProduct() {
        Long id = 1L;
        Product product = mock(Product.class);
        when(findProductOutputPort.findById(id)).thenReturn(Optional.of(product));

        var response = useCase.findById(id);

        assertSame(product, response);
        verify(findProductOutputPort).findById(id);
    }

    @Test
    void findById_shouldThrowProductNotFound() {
        Long id = 1L;
        when(findProductOutputPort.findById(id)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> useCase.findById(id));
    }

    @Test
    void findByCategory_shouldReturnListOfProducts() {
        var categoryString = "BURGER";
        var categoryEnum = Category.BURGER;
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        List<Product> products = List.of(product1, product2);
        when(findProductOutputPort.findByCategory(categoryEnum)).thenReturn(products);
        when(useCase.findByCategory(categoryString)).thenReturn(products);

        var response = useCase.findByCategory(categoryString);

        assertSame(products, response);
        assertEquals(2, response.size());
        verify(findProductOutputPort).findByCategory(categoryEnum);
    }

    @Test
    void findByCategory_shouldReturnEmptyListWhenNoProducts() {
        var categoryString = "BURGER";
        var categoryEnum = Category.BURGER;
        List<Product> noProducts = List.of();
        when(findProductOutputPort.findByCategory(categoryEnum)).thenReturn(noProducts);
        when(useCase.findByCategory(categoryString)).thenReturn(noProducts);

        var response = useCase.findByCategory(categoryString);

        assertSame(noProducts, response);
        assertEquals(0, response.size());
        verify(findProductOutputPort).findByCategory(categoryEnum);
    }
}