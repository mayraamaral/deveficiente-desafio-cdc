package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.purchase.Purchase;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseRequestDTO;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseResponseDTO;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItem;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItemRequestDTO;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.repositories.BookRepository;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import dev.mayra.seeddesafiocdc.repositories.PurchaseRepository;
import dev.mayra.seeddesafiocdc.repositories.StateRepository;
import dev.mayra.seeddesafiocdc.utils.exceptions.InvalidRequestException;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchase")
@Tag(name = "Purchase", description = "Operations related to the Purchase entity")
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final BookRepository bookRepository;

    public PurchaseController(PurchaseRepository purchaseRepository, CountryRepository countryRepository, StateRepository stateRepository, BookRepository bookRepository) {
        this.purchaseRepository = purchaseRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.bookRepository = bookRepository;
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
        List<PurchaseItem> itemsEntity = itemRequestListToItem(purchase);
        created.addAllItems(itemsEntity);

        if(!created.isEqualToCalculatedTotal(purchase.getTotal())) {
            throw new InvalidRequestException("Total given is different than calculated total");
        }

        return ResponseEntity.ok().body(purchaseRepository.save(created).toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponseDTO>> listAll() {
        return ResponseEntity.ok().body(purchaseRepository.findAllWithItems()
            .stream().map(Purchase::toResponseDTO)
            .toList());
    }

    private List<PurchaseItem> itemRequestListToItem(PurchaseRequestDTO dto) {
        List<Long> bookIds = extractBookIds(dto);

        Map<Long, Book> bookMap = bookRepository.findAllById(bookIds).stream()
            .collect(Collectors.toMap(Book::getId, book -> book));

        validateBooksExist(bookIds, bookMap);

        return dto.getItems().stream()
            .map(item -> new PurchaseItem(bookMap.get(item.getBookId()), item.getQuantity()))
            .toList();
    }

    private List<Long> extractBookIds(PurchaseRequestDTO dto) {
        return dto.getItems().stream()
            .map(PurchaseItemRequestDTO::getBookId)
            .toList();
    }

    private void validateBooksExist(List<Long> bookIds, Map<Long, Book> bookMap) {
        if (bookIds.size() != bookMap.size()) {
            List<Long> missingIds = bookIds.stream()
                .filter(id -> !bookMap.containsKey(id))
                .toList();
            throw new NotFoundException("Books not found with ids: " + missingIds);
        }
    }
}
