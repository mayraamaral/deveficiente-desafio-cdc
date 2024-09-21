package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.controllers.docs.PurchaseControllerDoc;
import dev.mayra.seeddesafiocdc.model.purchase.*;
import dev.mayra.seeddesafiocdc.repositories.*;
import dev.mayra.seeddesafiocdc.services.PurchaseService;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@Tag(name = "Purchase", description = "Operations related to the Purchase entity")
public class PurchaseController implements PurchaseControllerDoc {
    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseService purchaseService, PurchaseRepository purchaseRepository) {
        this.purchaseService = purchaseService;
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> create(@RequestBody @Valid PurchaseRequestDTO purchase) {
        return ResponseEntity.ok().body(purchaseService.create(purchase)
            .toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponseDTO>> listAll() {
        return ResponseEntity.ok().body(purchaseRepository.findAllWithItems()
            .stream().map(Purchase::toResponseDTO)
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponseDTO> listById(@PathVariable Long id) {
        Purchase found = purchaseRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Purchase not found"));

        return ResponseEntity.ok().body(found.toResponseDTO());
    }

    @GetMapping("/{id}/minified")
    public ResponseEntity<PurchaseMinifiedDTO> listByIdMinified(@PathVariable Long id) {
        PurchaseMinifiedProjection found = purchaseRepository.findByIdMinified(id)
            .orElseThrow(() -> new NotFoundException("Purchase not found"));

        return ResponseEntity.ok().body(new PurchaseMinifiedDTO(found));
    }

}
