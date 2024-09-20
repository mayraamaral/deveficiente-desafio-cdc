package dev.mayra.seeddesafiocdc.model.purchase;

import dev.mayra.seeddesafiocdc.model.country.CountryResponseDTO;
import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItemResponseDTO;
import dev.mayra.seeddesafiocdc.model.state.StateResponseDTO;

import java.util.List;

public class PurchaseResponseDTO {
    private Long id;
    private String name;
    private String lastname;
    private String document;
    private String documentType;
    private String address;
    private String addressSecondLine;
    private String zipCode;
    private String city;
    private StateResponseDTO state;
    private CountryResponseDTO country;
    private String contact;

    private List<PurchaseItemResponseDTO> items;

    private Double subtotal;

    private Double total;

    private String couponCode;

    public PurchaseResponseDTO(Long id, String name, String lastname, String document, String documentType,
                               String address, String addressSecondLine, String zipCode, String city, StateResponseDTO state,
                               CountryResponseDTO country, String contact, List<PurchaseItemResponseDTO> items,
                               Double subtotal, Double total, String couponCode) {
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
        this.items = items;
        this.subtotal = subtotal;
        this.total = total;
        this.couponCode = couponCode;
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

    public String getDocumentType() {
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

    public StateResponseDTO getState() {
        return state;
    }

    public CountryResponseDTO getCountry() {
        return country;
    }

    public String getContact() {
        return contact;
    }

    public List<PurchaseItemResponseDTO> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public String getCouponCode() {
        return couponCode;
    }
}
