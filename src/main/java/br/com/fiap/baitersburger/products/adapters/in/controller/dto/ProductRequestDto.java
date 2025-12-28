package br.com.fiap.baitersburger.products.adapters.in.controller.dto;

import br.com.fiap.baitersburger.products.core.domain.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductRequestDto(@NotBlank String productName,
                                @NotNull Category category,
                                @NotNull BigDecimal price,
                                @NotBlank String description,
                                @NotEmpty List<String> imagesUrls) {
}
