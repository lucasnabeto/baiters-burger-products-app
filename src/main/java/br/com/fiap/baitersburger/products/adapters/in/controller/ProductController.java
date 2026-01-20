package br.com.fiap.baitersburger.products.adapters.in.controller;

import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductRequestDto;
import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductResponseDto;
import br.com.fiap.baitersburger.products.adapters.in.controller.mapper.ProductMapper;
import br.com.fiap.baitersburger.products.core.application.ports.in.DeleteProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.FindProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.InsertProductInputPort;
import br.com.fiap.baitersburger.products.core.application.ports.in.UpdateProductInputPort;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final DeleteProductInputPort deleteProductInputPort;
    private final FindProductInputPort findProductInputPort;
    private final InsertProductInputPort insertProductInputPort;
    private final UpdateProductInputPort updateProductInputPort;
    private final ProductMapper mapper;

    public ProductController(DeleteProductInputPort deleteProductInputPort,
                             FindProductInputPort findProductInputPort,
                             InsertProductInputPort insertProductInputPort,
                             UpdateProductInputPort updateProductInputPort,
                             ProductMapper mapper) {
        this.deleteProductInputPort = deleteProductInputPort;
        this.findProductInputPort = findProductInputPort;
        this.insertProductInputPort = insertProductInputPort;
        this.updateProductInputPort = updateProductInputPort;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductsById(@PathVariable Long id){
        Product product = findProductInputPort.findById(id);
        var response = mapper.toProductResponseDto(product);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findProductsByCategory(@RequestParam String category){
        List<Product> products = findProductInputPort.findByCategory(category);
        var response = products.stream()
                .map(mapper::toProductResponseDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> insertProduct(@Valid @RequestBody ProductRequestDto dto){
        Product product = mapper.toProduct(dto);
        insertProductInputPort.insert(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto dto){
        Product product = mapper.toProduct(dto);
        product.setId(id);
        updateProductInputPort.update(product);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        deleteProductInputPort.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
