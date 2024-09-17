package dev.mayra.seeddesafiocdc.model.category;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDTO {

    @NotBlank(message = "Name can't be null or empty")
    @UniqueValue(fieldName = "name", domainClass = Category.class, message = "Name already exists")
    @Schema(description = "Fill the category name", required = true, example = "Romance")
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
