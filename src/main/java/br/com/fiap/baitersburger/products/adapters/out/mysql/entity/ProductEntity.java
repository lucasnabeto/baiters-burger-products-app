package br.com.fiap.baitersburger.products.adapters.out.mysql.entity;

import br.com.fiap.baitersburger.products.core.domain.model.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private BigDecimal price;

    private String description;

    private List<String> imagesUrls;
}
