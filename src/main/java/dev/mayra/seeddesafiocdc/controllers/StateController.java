package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.controllers.docs.StateControllerDoc;
import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.model.state.StateRequestDTO;
import dev.mayra.seeddesafiocdc.model.state.StateWithCountryResponseDTO;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import dev.mayra.seeddesafiocdc.repositories.StateRepository;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@Tag(name = "State", description = "Operations related to the State entity")
public class StateController implements StateControllerDoc {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateController(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    @PostMapping
    public ResponseEntity<StateWithCountryResponseDTO> create(@RequestBody @Valid StateRequestDTO state) {
        Country found = countryRepository.findById(state.getCountryId())
            .orElseThrow(() -> new NotFoundException("Country not found"));

        return ResponseEntity.ok().body(
            stateRepository.save(new State(state, found))
                .toWithStateResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<StateWithCountryResponseDTO>> listAll() {
        return ResponseEntity.ok().body(
            stateRepository.findAll()
                .stream().map(State::toWithStateResponseDTO)
                .toList());
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<List<StateWithCountryResponseDTO>> listAllByCountryId(@PathVariable Long countryId) {
        return ResponseEntity.ok().body(
            stateRepository.findAllByCountry_Id(countryId)
                .stream().map(State::toWithStateResponseDTO)
                .toList());
    }
}
