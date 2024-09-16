package dev.mayra.seeddesafiocdc.model.category;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Deprecated
    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Category(CategoryRequestDTO dto) {
        this.name = dto.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryResponseDTO toResponseDTO() {
        return new CategoryResponseDTO(id, name);
    }
}
