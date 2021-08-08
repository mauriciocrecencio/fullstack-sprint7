package br.com.rchlo.store.repository;

import br.com.rchlo.store.domain.Category;
import br.com.rchlo.store.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
