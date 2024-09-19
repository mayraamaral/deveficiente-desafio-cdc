package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.payment.Payment;
import dev.mayra.seeddesafiocdc.model.payment.PaymentRequestDTO;
import dev.mayra.seeddesafiocdc.model.payment.PaymentResponseDTO;
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
@RequestMapping("/payment")
@Tag(name = "Payment", description = "Operations related to the Payment entity")
public class PaymentController {
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    List<Payment> payments = new ArrayList<>();

    public PaymentController(CountryRepository countryRepository, StateRepository stateRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@RequestBody @Valid PaymentRequestDTO payment) {
        Country country = countryRepository.findById(payment.getCountryId())
            .orElseThrow(() -> new NotFoundException("Country not found"));

        State state = null;

        if(country.hasStates()) {
            state = stateRepository.findById(payment.getStateId())
                .orElseThrow(() -> new NotFoundException("State not found"));
        }

        Payment created = new Payment(payment, country, state);
        payments.add(created);

        return ResponseEntity.ok().body(created.toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> listAll() {
        return ResponseEntity.ok().body(payments.stream().map(Payment::toResponseDTO).toList());
    }
}
