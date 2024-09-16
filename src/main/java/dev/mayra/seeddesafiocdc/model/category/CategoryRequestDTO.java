package dev.mayra.seeddesafiocdc.model.category;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import jakarta.validation.constraints.NotNull;

public class CategoryRequestDTO {

    @NotNull(message = "Name can't be null")
    @UniqueValue(fieldName = "name", domainClass = Category.class, message = "Name already exists")
    private String name;

    @Deprecated
    public CategoryRequestDTO() {}

    public CategoryRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
