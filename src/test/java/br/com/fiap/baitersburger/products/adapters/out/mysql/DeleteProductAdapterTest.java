package br.com.fiap.baitersburger.products.adapters.out.mysql;

import br.com.fiap.baitersburger.products.adapters.out.mysql.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteProductAdapterTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private DeleteProductAdapter adapter;

    @Test
    void delete_shouldRemoveEntity() {
        Long id = 1L;

        adapter.delete(id);

        verify(repository).deleteById(id);
    }
}