package dev.mayra.seeddesafiocdc.model.purchase;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItem;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItemResponseDTO;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.model.state.StateResponseDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Purchase {
    private Long id;
    private String name;
    private String lastname;
    private String document;
    private DocumentType documentType;
    private String address;
    private String addressSecondLine;
    private String zipCode;
    private String city;
    private State state;
    private Country country;
    private String contact;

    private List<PurchaseItem> items = new ArrayList<>();

    private Double total;

    public Purchase(Long id, String name, String lastname, String document, DocumentType documentType, String address,
                    String addressSecondLine, String zipCode, String city, State state, Country country, String contact,
                    BigDecimal total) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.document = document;
        this.documentType = documentType;
        this.address = address;
        this.addressSecondLine = addressSecondLine;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.contact = contact;
    }

    public Purchase(PurchaseRequestDTO dto, Country country, State state) {
        this.name = dto.getName();
        this.lastname = dto.getLastname();
        this.document = dto.getDocument();
        this.documentType = DocumentType.valueOf(dto.getDocumentType());
        this.address = dto.getAddress();
        this.addressSecondLine = dto.getAddressSecondLine();
        this.zipCode = dto.getZipCode();
        this.city = dto.getZipCode();
        this.state = state;
        this.country = country;
        this.contact = dto.getContact();
        this.total = dto.getTotal();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDocument() {
        return document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public Country getCountry() {
        return country;
    }

    public String getContact() {
        return contact;
    }

    public Double getTotal() {
        return total;
    }

    public Double calculateTotal() {
        return items.stream()
            .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
            .sum();
    }

    public boolean isEqualToCalculatedTotal(Double total) {
        return Objects.equals(total, calculateTotal());
    }

    public PurchaseResponseDTO toResponseDTO() {
        StateResponseDTO stateResponse = Optional.ofNullable(state)
            .map(State::toResponseDTO).orElse(null);

        return new PurchaseResponseDTO(id, name, lastname, document, documentType.getDescription(), address,
            addressSecondLine, zipCode, city, stateResponse, country.toResponseDTO(), contact, itemsToResponseDTO(),
            total);

    }

    public void addAllItems(List<PurchaseItem> items) {
        items.forEach(item -> item.setPurchase(this));
        this.items.addAll(items);
    }

    public List<PurchaseItemResponseDTO> itemsToResponseDTO() {
        return items.stream().map(i ->
                new PurchaseItemResponseDTO(i.getId(), i.getBook().toMinifiedDTO(), i.getQuantity()))
            .toList();
    }
}
