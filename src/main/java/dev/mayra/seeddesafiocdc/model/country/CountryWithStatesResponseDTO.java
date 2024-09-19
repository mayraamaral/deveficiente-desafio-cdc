package dev.mayra.seeddesafiocdc.model.country;

import dev.mayra.seeddesafiocdc.model.state.StateResponseDTO;

import java.util.List;

public class CountryWithStatesResponseDTO extends CountryResponseDTO {
    private List<StateResponseDTO> states;

    public CountryWithStatesResponseDTO(Long id, String name, List<StateResponseDTO> states) {
        super(id, name);
        this.states = states;
    }

    public CountryWithStatesResponseDTO(CountryWithStatesProjection projection) {
        this(projection.getId(),
            projection.getName(),
            projection.getStatesWithoutCountryResponseDTO());
    }

    public List<StateResponseDTO> getStates() {
        return states;
    }
}
