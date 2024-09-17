package dev.mayra.seeddesafiocdc.model.country;

import dev.mayra.seeddesafiocdc.model.state.StateWithoutCountryResponseDTO;

import java.util.List;

public interface CountryWithStatesProjection {
    Long getId();
    String getName();
    List<StateProjection> getStates();

    interface StateProjection {
        Long getId();
        String getName();
    }

    default List<StateWithoutCountryResponseDTO> getStateWithoutCountryResponseDTO() {
        return getStates()
            .stream().map(StateWithoutCountryResponseDTO::new).toList();
    }
}
