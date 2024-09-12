package org.productos.webflux.wsproductos.application.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService<T, I> {
    Flux<T> getAll();

    Mono<T> getById(I id);

    Mono<T> save(T t);

    Mono<T> update(T t, I id);

    Mono<Void> delete(I id);
}
