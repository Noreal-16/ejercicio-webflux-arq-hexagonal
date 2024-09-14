package org.productos.webflux.wsproductos.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.productos.webflux.wsproductos.application.implement.productImp.dto.ProductDto;
import org.productos.webflux.wsproductos.application.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDto> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDto> getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDto> createProduct(@RequestBody @Validated ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Validated ProductDto productDto) {
        return productService.update(productDto, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }

}
