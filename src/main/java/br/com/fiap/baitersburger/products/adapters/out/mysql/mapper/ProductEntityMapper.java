package br.com.fiap.baitersburger.products.adapters.out.mysql.mapper;

import br.com.fiap.baitersburger.products.adapters.out.mysql.entity.ProductEntity;
import br.com.fiap.baitersburger.products.core.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toProduct(ProductEntity entity);

    ProductEntity toProductEntity(Product product);
}
