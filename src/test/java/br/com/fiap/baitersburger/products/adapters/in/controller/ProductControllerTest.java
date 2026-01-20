package br.com.fiap.baitersburger.products.adapters.in.controller;

import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductRequestDto;
import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductResponseDto;
import br.com.fiap.baitersburger.products.adapters.in.controller.mapper.ProductMapper;
import br.com.fiap.baitersburger.products.core.application.ports.in.DeleteProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.InsertProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.UpdateProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private DeleteProductInputPort deleteProductInputPort;

    @Mock
    private FindProductInputPort findProductInputPort;

    @Mock
    private InsertProductInputPort insertProductInputPort;

    @Mock
    private UpdateProductInputPort updateProductInputPort;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductController productController;

    @Test
    void findProductsById_shouldReturnProductResponse() {
        Long id = 1L;
        Product product = mock(Product.class);
        ProductResponseDto responseDto = mock(ProductResponseDto.class);
        when(findProductInputPort.findById(id)).thenReturn(product);
        when(mapper.toProductResponseDto(product)).thenReturn(responseDto);

        ResponseEntity<ProductResponseDto> response = productController.findProductsById(id);

        verify(findProductInputPort).findById(id);
        verify(mapper).toProductResponseDto(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(responseDto, response.getBody());
    }

    @Test
    void findProductsByCategory_shouldReturnListOfResponses() {
        String category = "burger";
        Product product = mock(Product.class);
        ProductResponseDto responseDto = mock(ProductResponseDto.class);
        when(findProductInputPort.findByCategory(category)).thenReturn(List.of(product));
        when(mapper.toProductResponseDto(product)).thenReturn(responseDto);

        ResponseEntity<List<ProductResponseDto>> response = productController.findProductsByCategory(category);

        verify(findProductInputPort).findByCategory(category);
        verify(mapper).toProductResponseDto(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertSame(responseDto, response.getBody().getFirst());
    }

    @Test
    void insertProduct_shouldCallInsertAndReturnCreated() {
        ProductRequestDto requestDto = mock(ProductRequestDto.class);
        Product product = mock(Product.class);
        when(mapper.toProduct(requestDto)).thenReturn(product);

        ResponseEntity<Void> response = productController.insertProduct(requestDto);

        verify(mapper).toProduct(requestDto);
        verify(insertProductInputPort).insert(product);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateProduct_shouldSetIdCallUpdateAndReturnNoContent() {
        Long id = 2L;
        ProductRequestDto requestDto = mock(ProductRequestDto.class);
        Product product = mock(Product.class);
        when(mapper.toProduct(requestDto)).thenReturn(product);

        ResponseEntity<Void> response = productController.updateProduct(id, requestDto);

        verify(mapper).toProduct(requestDto);
        verify(product).setId(id);
        verify(updateProductInputPort).update(product);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteProduct_shouldCallDeleteAndReturnNoContent() {
        Long id = 3L;

        ResponseEntity<Void> response = productController.deleteProduct(id);

        verify(deleteProductInputPort).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}