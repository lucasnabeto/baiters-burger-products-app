package br.com.fiap.baitersburger.products.adapters.in.controller.mapper;

import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductRequestDto;
import br.com.fiap.baitersburger.products.adapters.in.controller.dto.ProductResponseDto;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequestDto requestDto);

    ProductResponseDto toProductResponseDto(Product product);
}