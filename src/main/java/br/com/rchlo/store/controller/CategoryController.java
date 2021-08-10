package br.com.rchlo.store.controller;

import br.com.rchlo.store.dto.CategoryDto;
import br.com.rchlo.store.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
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
}
