package org.productos.webflux.wsproductos.domain.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "CATEGORY")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryEntity {
    Long id;
    String name;
    String description;
    String status;
}
