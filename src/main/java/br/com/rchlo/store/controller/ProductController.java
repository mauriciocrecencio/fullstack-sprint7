package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.product.ProductByColorDto;
import br.com.rchlo.store.dto.product.ProductDto;
import br.com.rchlo.store.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/products")
  @Cacheable(value = "productsList")
  public List<ProductDto> products(@PageableDefault(sort = "name") Pageable pagination) {
    return productRepository.findAll(pagination).stream().map(ProductDto::new)
                            .collect(Collectors.toList());
  }

  @GetMapping("/products/clear-cache")
  @CacheEvict(value = "productsList", allEntries = true)
  public void productsClearCache() {
//        Do nothing because it only clears the cache of products
  }

  @GetMapping("/reports/products/by-color")
  public List<ProductByColorDto> productByColorReport() {
    return productRepository.productsByColor();
  }

}
