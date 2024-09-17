package dev.mayra.seeddesafiocdc.model.state;

import dev.mayra.seeddesafiocdc.model.country.CountryWithStatesProjection;

public class StateWithoutCountryResponseDTO {
    private Long id;
    private String name;

    public StateWithoutCountryResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateWithoutCountryResponseDTO(CountryWithStatesProjection.StateProjection projection) {
        this.id = projection.getId();
        this.name = projection.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
