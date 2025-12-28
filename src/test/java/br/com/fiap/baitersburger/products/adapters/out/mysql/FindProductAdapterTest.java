package br.com.fiap.baitersburger.products.adapters.out.mysql;

import br.com.fiap.baitersburger.products.adapters.out.mysql.entity.ProductEntity;
import br.com.fiap.baitersburger.products.adapters.out.mysql.mapper.ProductEntityMapper;
import br.com.fiap.baitersburger.products.adapters.out.mysql.repository.ProductRepository;
import br.com.fiap.baitersburger.products.core.domain.model.Category;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
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
class FindProductAdapterTest {
    @Mock
    private ProductEntityMapper mapper;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private FindProductAdapter adapter;

    @Test
    void findById_shouldReturnProduct() {
        Long id = 1L;
        Product product = mock(Product.class);
        ProductEntity entity = mock(ProductEntity.class);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toProduct(entity)).thenReturn(product);

        var response = adapter.findById(id);

        assertTrue(response.isPresent());
        assertSame(product, response.get());
        verify(repository).findById(id);
        verify(mapper).toProduct(entity);
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        Long id = 2L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        var response = adapter.findById(id);

        assertTrue(response.isEmpty());
        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }

    @Test
    void findByCategory_shouldReturnListOfProducts() {
        var category = Category.BURGER;
        ProductEntity entity1 = mock(ProductEntity.class);
        ProductEntity entity2 = mock(ProductEntity.class);
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        when(repository.findByCategory(category)).thenReturn(List.of(entity1, entity2));
        when(mapper.toProduct(entity1)).thenReturn(product1);
        when(mapper.toProduct(entity2)).thenReturn(product2);

        var response = adapter.findByCategory(category);

        assertEquals(2, response.size());
        assertSame(product1, response.get(0));
        assertSame(product2, response.get(1));
        verify(repository).findByCategory(category);
        verify(mapper).toProduct(entity1);
        verify(mapper).toProduct(entity2);
    }

    @Test
    void findByCategory_shouldReturnEmptyListWhenNoProducts() {
        var category = Category.BURGER;
        when(repository.findByCategory(category)).thenReturn(List.of());

        var response = adapter.findByCategory(category);

        assertTrue(response.isEmpty());
        verify(repository).findByCategory(category);
        verifyNoInteractions(mapper);
    }
}