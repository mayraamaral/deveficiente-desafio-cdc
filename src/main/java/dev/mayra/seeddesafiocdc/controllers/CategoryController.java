package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.controllers.docs.CategoryControllerDoc;
import dev.mayra.seeddesafiocdc.model.category.Category;
import dev.mayra.seeddesafiocdc.model.category.CategoryRequestDTO;
import dev.mayra.seeddesafiocdc.model.category.CategoryResponseDTO;
import dev.mayra.seeddesafiocdc.repositories.CategoryRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Operations related to the Category entity")
public class CategoryController implements CategoryControllerDoc {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO category) {
        CategoryResponseDTO created = categoryRepository
            .save(new Category(category)).toResponseDTO();

        return ResponseEntity.ok().body(created);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.ok().body(categoryRepository.findAll()
            .stream().map(Category::toResponseDTO)
            .toList());
    }
}
