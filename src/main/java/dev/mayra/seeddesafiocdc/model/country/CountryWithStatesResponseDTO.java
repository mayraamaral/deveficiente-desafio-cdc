package dev.mayra.seeddesafiocdc.model.country;

import dev.mayra.seeddesafiocdc.model.state.StateWithoutCountryResponseDTO;

import java.util.List;
import java.util.Optional;

public class CountryWithStatesResponseDTO extends CountryResponseDTO {
    private List<StateWithoutCountryResponseDTO> states;

    public CountryWithStatesResponseDTO(Long id, String name, List<StateWithoutCountryResponseDTO> states) {
        super(id, name);
        this.states = states;
    }

    public CountryWithStatesResponseDTO(CountryWithStatesProjection projection) {
        this(projection.getId(),
            projection.getName(),
            Optional.ofNullable(projection.getStateWithoutCountryResponseDTO()).orElse(List.of()));
    }

    public List<StateWithoutCountryResponseDTO> getStates() {
        return states;
    }
}
