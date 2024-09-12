package org.productos.webflux.wsproductos.application.implement.productImp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    String name;
    @NotNull(message = "price cannot be null")
    double price;
    @NotNull(message = "amount cannot be null")
    int amount;
    String description;
    String status;
}
