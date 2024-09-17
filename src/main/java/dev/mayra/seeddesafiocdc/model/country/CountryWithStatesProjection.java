package dev.mayra.seeddesafiocdc.model.country;

import dev.mayra.seeddesafiocdc.model.state.StateWithoutCountryResponseDTO;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

public interface CountryWithStatesProjection {
    Long getId();
    String getName();
    List<StateProjection> getStates();

    interface StateProjection {
        Long getId();
        String getName();
    }

    default List<StateWithoutCountryResponseDTO> getStatesWithoutCountryResponseDTO() {
        return Optional.ofNullable(getStates())
            .map(states -> states.stream()
                .map(StateWithoutCountryResponseDTO::new)
                .toList())
            .orElse(List.of());
    }
}
