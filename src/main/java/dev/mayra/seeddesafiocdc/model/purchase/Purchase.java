package dev.mayra.seeddesafiocdc.model.purchase;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.coupon.Coupon;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItem;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItemResponseDTO;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.model.validation.ValidationResult;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Purchase {

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastname;

    @Column
    private String document;

    @Column(name = "document_type")
    @Enumerated(value = EnumType.STRING)
    private DocumentType documentType;

    @Column
    private String address;

    @Column(name = "address_second_line")
    private String addressSecondLine;

    @Column(name = "zip_code")
    private String zipCode;

    @Column
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column
    private String contact;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> items = new ArrayList<>();

    @Column
    private Double subtotal;

    @Column
    private Double total;

    @ManyToOne
    @JoinColumn(name = "coupon_code", referencedColumnName = "code")
    private Coupon coupon;

    @Deprecated
    public Purchase() {}

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

    public Purchase(PurchaseRequestDTO dto, Country country, Optional<State> possibleState) {
        this.name = dto.getName();
        this.lastname = dto.getLastname();
        this.document = StringUtils.getDigits(dto.getDocument());
        this.documentType = DocumentType.valueOf(dto.getDocumentType());
        this.address = dto.getAddress();
        this.addressSecondLine = dto.getAddressSecondLine();
        this.zipCode = dto.getZipCode();
        this.city = dto.getCity();
        this.country = country;
        this.contact = dto.getContact();
        this.total = dto.getTotal();
        possibleState.ifPresent(state -> this.state = state);
    }

    public ValidationResult applyDiscount(Coupon coupon) {
        ValidationResult validation = coupon.validate();

        if(validation.isValid()) {
            applyDiscountPercentageInTotal(coupon.getPercentage());
        }

        this.coupon = coupon;
        return validation;
    }

    private void applyDiscountPercentageInTotal(Integer percentage) {
        total = subtotal - (subtotal * ((double) percentage/100));
    }

    public Double calculateSubtotal() {
        subtotal = items.stream()
            .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
            .sum();

        return subtotal;
    }

    public boolean isEqualToCalculatedSubtotal(Double total) {
        return Objects.equals(total, calculateSubtotal());
    }

    public boolean hasCoupon() {
        return Optional.ofNullable(coupon).isPresent();
    }

    public String getCouponOrNull() {
        if(!hasCoupon()) {
            return null;
        }

        return coupon.getCode();
    }

    public PurchaseResponseDTO toResponseDTO() {
        String stateNameResponse = Optional.ofNullable(state)
            .map(State::getName).orElse(null);

        return new PurchaseResponseDTO(id, name, lastname, document, documentType.getDescription(), address,
            addressSecondLine, zipCode, city, stateNameResponse, country.getName(), contact, itemsToResponseDTO(),
            subtotal, total,
            Optional.ofNullable(coupon)
                .map(Coupon::getCode)
                .orElse(null));
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
}
