package br.com.rchlo.store.dto;

public class ProductImageDto {
  private final String imageUrl;

  public ProductImageDto(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
