package br.com.rchlo.store.controller;

import br.com.rchlo.store.domain.Category;
import br.com.rchlo.store.dto.category.CategoryDto;
import br.com.rchlo.store.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryRepository categoryRepository;

  public CategoryController(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }


  @GetMapping
  public List<CategoryDto> list() {
    return categoryRepository.findAllByOrderByPosition().stream().map(CategoryDto::new).collect(
        Collectors.toList());
  }

  @GetMapping("/admin/{id}")
  public ResponseEntity<Category> listDetailedCategoryById(@PathVariable @Valid Long id) {
    Optional<Category> category = categoryRepository.findById(id);
    if(category.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Category detailedCategory = categoryRepository.getById(id);
    return ResponseEntity.ok().body(detailedCategory);
  }
}
