package br.com.fiap.baitersburger.products.adapters.out.mysql.repository;

import br.com.fiap.baitersburger.products.adapters.out.mysql.entity.ProductEntity;
import br.com.fiap.baitersburger.products.core.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(Category category);
}
