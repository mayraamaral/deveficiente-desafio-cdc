package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
