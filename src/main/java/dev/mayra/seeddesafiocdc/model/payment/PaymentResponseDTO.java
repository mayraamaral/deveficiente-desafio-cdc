package dev.mayra.seeddesafiocdc.model.payment;

import dev.mayra.seeddesafiocdc.model.country.CountryResponseDTO;
import dev.mayra.seeddesafiocdc.model.state.StateResponseDTO;

public class PaymentResponseDTO {
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

    public PaymentResponseDTO(Long id, String name, String lastname, String document, String documentType,
                              String address, String addressSecondLine, String zipCode, String city, StateResponseDTO state,
                              CountryResponseDTO country, String contact) {
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
}
