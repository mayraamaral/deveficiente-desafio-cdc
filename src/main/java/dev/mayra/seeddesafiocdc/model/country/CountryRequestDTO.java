package dev.mayra.seeddesafiocdc.model.country;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CountryRequestDTO {
    @NotBlank(message = "Name can't be null or empty")
    @UniqueValue(fieldName = "name", domainClass = Country.class, message = "Country already exists")
    @Schema(description = "Fill the country name", required = true, example = "Brasil")
    private String name;

    @Deprecated
    public CountryRequestDTO() {}

    public CountryRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
