package br.com.fiap.baitersburger.products.adapters.out.mysql;

import br.com.fiap.baitersburger.products.adapters.out.mysql.entity.ProductEntity;
import br.com.fiap.baitersburger.products.adapters.out.mysql.mapper.ProductEntityMapper;
import br.com.fiap.baitersburger.products.adapters.out.mysql.repository.ProductRepository;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductAdapterTest {
    @Mock
    private ProductEntityMapper mapper;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private UpdateProductAdapter adapter;

    @Test
    void update_shouldSaveEntity() {
        Product product = mock(Product.class);
        ProductEntity entity = mock(ProductEntity.class);
        when(mapper.toProductEntity(product)).thenReturn(entity);

        adapter.update(product);

        verify(mapper).toProductEntity(product);
        verify(repository).save(entity);
    }
}