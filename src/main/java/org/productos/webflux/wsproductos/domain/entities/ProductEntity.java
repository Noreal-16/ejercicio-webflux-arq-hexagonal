package org.productos.webflux.wsproductos.domain.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "PRODUCT")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {
    @Id
    Long id;
    String name;
    double price;
    int amount;
    String description;
    String status;
}
