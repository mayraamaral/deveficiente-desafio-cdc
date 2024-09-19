package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.purchase.Purchase;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseRequestDTO;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseResponseDTO;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import dev.mayra.seeddesafiocdc.repositories.StateRepository;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchase")
@Tag(name = "Purchase", description = "Operations related to the Purchase entity")
public class PurchaseController {
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    List<Purchase> purchases = new ArrayList<>();

    public PurchaseController(CountryRepository countryRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> create(@RequestBody @Valid PurchaseRequestDTO purchase) {
        Country country = countryRepository.findById(purchase.getCountryId())
            .orElseThrow(() -> new NotFoundException("Country not found"));

        State state = null;

        if(country.hasStates()) {
            state = stateRepository.findById(purchase.getStateId())
                .orElseThrow(() -> new NotFoundException("State not found"));
        }

        Purchase created = new Purchase(purchase, country, state);
        purchases.add(created);

        return ResponseEntity.ok().body(created.toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponseDTO>> listAll() {
        return ResponseEntity.ok().body(purchases.stream().map(Purchase::toResponseDTO).toList());
    }
}
