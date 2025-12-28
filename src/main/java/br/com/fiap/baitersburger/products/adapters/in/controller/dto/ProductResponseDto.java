package br.com.fiap.baitersburger.products.adapters.in.controller.dto;

import br.com.fiap.baitersburger.products.core.domain.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(@JsonProperty("product") String productName,
                                 Category category,
                                 BigDecimal price,
                                 String description,
                                 @JsonProperty("images") List<String> imagesUrls) {
}
