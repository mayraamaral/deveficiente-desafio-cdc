package dev.mayra.seeddesafiocdc.model.state;

import dev.mayra.seeddesafiocdc.model.country.CountryResponseDTO;

public class StateWithCountryResponseDTO {
    private Long id;
    private String name;
    private CountryResponseDTO country;

    public StateWithCountryResponseDTO(Long id, String name, CountryResponseDTO country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CountryResponseDTO getCountry() {
        return country;
    }
}
