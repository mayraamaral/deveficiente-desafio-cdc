package dev.mayra.seeddesafiocdc.services;

import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.coupon.Coupon;
import dev.mayra.seeddesafiocdc.model.purchase.Purchase;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseRequestDTO;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItem;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItemRequestDTO;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.model.validation.ValidationResult;
import dev.mayra.seeddesafiocdc.repositories.*;
import dev.mayra.seeddesafiocdc.utils.exceptions.InvalidRequestException;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final BookRepository bookRepository;
    private final CouponRepository couponRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, CountryRepository countryRepository,
                           StateRepository stateRepository, BookRepository bookRepository,
                           CouponRepository couponRepository) {
        this.purchaseRepository = purchaseRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.bookRepository = bookRepository;
        this.couponRepository = couponRepository;
    }

    public Purchase create(PurchaseRequestDTO dto) {
        Country country = countryRepository.findById(dto.getCountryId())
            .orElseThrow(() -> new NotFoundException("Country not found"));

        Optional<State> possibleState = getStateIfCountryHasStates(country, dto);

        Purchase created = new Purchase(dto, country, possibleState);

        addItemsToPurchase(created, dto);

        if(!created.isEqualToCalculatedSubtotal(dto.getTotal())) {
            throw new InvalidRequestException("Total given is different than calculated total");
        }

        applyCouponIfCouponCodeExists(dto, created);

        return purchaseRepository.save(created);

    }

    private void addItemsToPurchase(Purchase created, PurchaseRequestDTO dto) {
        List<PurchaseItem> itemsEntity = itemRequestListToItem(dto);
        created.addAllItems(itemsEntity);
    }

    private void applyCouponIfCouponCodeExists(PurchaseRequestDTO dto, Purchase created) {
        if(Optional.ofNullable(dto.getCouponCode()).isPresent()) {
            Coupon coupon = couponRepository.findByCode(dto.getCouponCode())
                .orElseThrow(() -> new NotFoundException("Coupon not found"));

            ValidationResult validation = created.applyDiscount(coupon);

            if(!validation.isValid()) {
                throw new InvalidRequestException(validation.getErrorMessage());
            }
        }
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

    private Optional<State> getStateIfCountryHasStates(Country country, PurchaseRequestDTO dto) {
        if(country.hasStates()) {
            State state = stateRepository.findById(dto.getStateId())
                .orElseThrow(() -> new NotFoundException("State not found"));

            return Optional.of(state);
        }

        return Optional.empty();
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
