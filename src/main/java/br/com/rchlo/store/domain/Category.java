package br.com.rchlo.store.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String slug;
  private int position;

  public void setName(String name) {
    this.name = name;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSlug() {
    return slug;
  }

  public int getPosition() {
    return position;
  }
}
