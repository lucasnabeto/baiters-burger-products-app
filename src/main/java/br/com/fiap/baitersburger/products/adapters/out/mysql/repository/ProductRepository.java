package br.com.fiap.baitersburger.products.adapters.out.mysql.repository;

import br.com.fiap.baitersburger.products.adapters.out.mysql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(String category);
}
