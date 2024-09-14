package org.productos.webflux.wsproductos.application.implement.categoryImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.productos.webflux.wsproductos.application.implement.categoryImp.dto.CategoryDto;
import org.productos.webflux.wsproductos.application.service.CategoryService;
import org.productos.webflux.wsproductos.domain.entities.CategoryEntity;
import org.productos.webflux.wsproductos.domain.repository.CategoryRepository;
import org.productos.webflux.wsproductos.infrastructure.utils.MapperConvert;
import org.productos.webflux.wsproductos.infrastructure.utils.enums.StatusEnums;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperConvert<CategoryDto, CategoryEntity> mapperConvert;

    @Override
    public Flux<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .flatMap(categories -> Mono.just(categories).filter(category -> category.getStatus().equals(StatusEnums.ACTIVE.toString())))
                .map(category -> mapperConvert.toDTO(category, CategoryDto.class))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<CategoryDto> getById(Long id) {
        return findCategoryById(id)
                .map(category -> mapperConvert.toDTO(category, CategoryDto.class));
    }

    @Override
    public Mono<CategoryDto> save(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = mapperConvert.toEntity(categoryDto, CategoryEntity.class);
        categoryEntity.setStatus(StatusEnums.ACTIVE.toString());
        return categoryRepository.save(categoryEntity)
                .doOnNext(data -> log.info("La data es {}", data.toString()))
                .map(categorySaved -> mapperConvert.toDTO(categorySaved, CategoryDto.class));
    }

    @Override
    public Mono<CategoryDto> update(CategoryDto categoryDto, Long id) {
        return findCategoryById(id).flatMap(category -> {
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            return categoryRepository.save(category)
                    .map(categorySaved -> mapperConvert.toDTO(categorySaved, CategoryDto.class));
        });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return findCategoryById(id).flatMap(infoCategory -> {
            infoCategory.setStatus(StatusEnums.DELETE.toString());
            return categoryRepository.save(infoCategory).then();
        });
    }

    private Mono<CategoryEntity> findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
