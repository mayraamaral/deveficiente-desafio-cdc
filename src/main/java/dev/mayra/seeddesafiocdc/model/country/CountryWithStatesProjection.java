package dev.mayra.seeddesafiocdc.model.country;

import dev.mayra.seeddesafiocdc.model.state.StateResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CountryWithStatesProjection {
    Long getId();
    String getName();
    List<StateProjection> getStates();

    interface StateProjection {
        Long getId();
        String getName();
    }

    default List<StateResponseDTO> getStatesWithoutCountryResponseDTO() {
        return Optional.ofNullable(getStates())
            .map(states -> states.stream()
                .map(StateResponseDTO::new)
                .toList())
            .orElse(List.of());
    }
}
