package org.productos.webflux.wsproductos.application.implement.productImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.productos.webflux.wsproductos.application.implement.productImp.dto.ProductDto;
import org.productos.webflux.wsproductos.application.service.ProductService;
import org.productos.webflux.wsproductos.domain.entities.ProductEntity;
import org.productos.webflux.wsproductos.domain.repository.ProductRepository;
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
public class ProductImp implements ProductService {

    private final ProductRepository productRepository;
    private final MapperConvert<ProductDto, ProductEntity> mapperConvert;

    @Override
    public Flux<ProductDto> getAll() {
        return productRepository.findAll()
                .map(products -> mapperConvert.toDTO(products, ProductDto.class))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<ProductDto> getById(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró producto por el id: " + id)))
                .map(products -> mapperConvert.toDTO(products, ProductDto.class));
    }

    @Override
    public Mono<ProductDto> save(ProductDto productDto) {
        ProductEntity productEntity = mapperConvert.toEntity(productDto, ProductEntity.class);
        return productRepository.save(productEntity)
                .map(productSave -> mapperConvert.toDTO(productSave, ProductDto.class));
    }

    @Override
    public Mono<ProductDto> update(ProductDto productDto, Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró producto por el id: " + id)))
                .flatMap(product -> {
                    product.setAmount(productDto.getAmount());
                    product.setName(productDto.getName());
                    product.setDescription(productDto.getDescription());
                    product.setPrice(productDto.getPrice());
                    product.setStatus(StatusEnums.ACTIVE.toString());
                    return productRepository.save(product)
                            .map(productSave -> mapperConvert.toDTO(productSave, ProductDto.class));
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró producto por el id: " + id)))
                .flatMap(productDelete -> {
                    if (productDelete.getStatus().equals(StatusEnums.DELETE.toString())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en proceso"));
                    }
                    productDelete.setStatus(StatusEnums.DELETE.toString());
                    return productRepository.save(productDelete).then();
                });
    }
}
