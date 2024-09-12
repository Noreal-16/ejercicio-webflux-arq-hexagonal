package org.productos.webflux.wsproductos.domain.repository;

import org.productos.webflux.wsproductos.domain.entities.CategoryEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends R2dbcRepository<CategoryEntity, Long> {
}
