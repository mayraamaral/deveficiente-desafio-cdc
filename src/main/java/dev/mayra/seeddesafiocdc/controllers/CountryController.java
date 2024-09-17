package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.country.CountryRequestDTO;
import dev.mayra.seeddesafiocdc.model.country.CountryResponseDTO;
import dev.mayra.seeddesafiocdc.model.country.CountryWithStatesResponseDTO;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@Tag(name = "Country", description = "Operations related to the Country entity")
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping
    public ResponseEntity<CountryResponseDTO> create(@RequestBody @Valid CountryRequestDTO country) {
        return ResponseEntity.ok().body(
            countryRepository.save(new Country(country))
                .toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<CountryResponseDTO>> listAll() {
        return ResponseEntity.ok().body(
            countryRepository.findAll()
                .stream().map(Country::toResponseDTO)
                .toList());
    }

    @GetMapping("/with-states")
    public ResponseEntity<List<CountryWithStatesResponseDTO>> listAllWithStates() {
        return ResponseEntity.ok().body(
            countryRepository.findAllWithStates()
                .stream().map(CountryWithStatesResponseDTO::new).toList());
    }
}
