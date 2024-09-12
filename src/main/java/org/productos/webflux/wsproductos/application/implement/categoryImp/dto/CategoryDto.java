package org.productos.webflux.wsproductos.application.implement.categoryImp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {
    Long id;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    String name;
    String description;
    String status;
}
