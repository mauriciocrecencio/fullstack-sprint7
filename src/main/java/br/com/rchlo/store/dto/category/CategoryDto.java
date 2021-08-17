package br.com.rchlo.store.dto.category;

import br.com.rchlo.store.domain.Category;

public class CategoryDto {
  private final String name;
  private final String slug;

  public CategoryDto(Category category) {
    this.name = category.getName();
    this.slug = category.getSlug();

  }

  public String getName() {
    return name;
  }

  public String getSlug() {
    return slug;
  }


}
