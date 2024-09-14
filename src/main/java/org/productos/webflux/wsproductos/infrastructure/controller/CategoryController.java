package org.productos.webflux.wsproductos.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.productos.webflux.wsproductos.application.implement.categoryImp.dto.CategoryDto;
import org.productos.webflux.wsproductos.application.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CategoryDto> getInfoCategory(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CategoryDto> registerCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody @Validated CategoryDto categoryDto) {
        return categoryService.update(categoryDto, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteCategory(@PathVariable Long id) {
        return categoryService.delete(id);
    }


}
