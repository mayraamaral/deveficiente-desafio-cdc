package dev.mayra.seeddesafiocdc.model.state;

import dev.mayra.seeddesafiocdc.model.country.CountryWithStatesProjection;

public class StateResponseDTO {
    private Long id;
    private String name;

    public StateResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateResponseDTO(CountryWithStatesProjection.StateProjection projection) {
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
