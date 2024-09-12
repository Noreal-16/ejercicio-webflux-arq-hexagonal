package org.productos.webflux.wsproductos.domain.repository;

import org.productos.webflux.wsproductos.domain.entities.ProductEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<ProductEntity, Long> {
}
