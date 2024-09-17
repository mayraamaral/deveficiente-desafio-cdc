package dev.mayra.seeddesafiocdc.model.state;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class StateRequestDTO {
    @NotBlank(message = "State name can't be null or empty")
    @UniqueValue(fieldName = "name", domainClass = State.class, message = "State already exists")
    private String name;

    @NotNull(message = "Country Id can't be null")
    @Positive(message = "Country id needs to be a positive number")
    private Long countryId;

    @Deprecated
    public StateRequestDTO() {}

    public StateRequestDTO(String name, Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }
}
