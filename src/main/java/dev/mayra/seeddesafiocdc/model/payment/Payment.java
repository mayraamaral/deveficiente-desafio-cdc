package dev.mayra.seeddesafiocdc.model.payment;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.state.State;

import java.util.Optional;

public class Payment {
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

    public Payment(Long id, String name, String lastname, String document, DocumentType documentType, String address,
                   String addressSecondLine, String zipCode, String city, State state, Country country, String contact) {
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

    public Payment(PaymentRequestDTO dto, Country country, State state) {
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

    public PaymentResponseDTO toResponseDTO() {
        if(Optional.ofNullable(state).isEmpty()) {
            return new PaymentResponseDTO(id, name, lastname, document, documentType.getDescription(), address,
                addressSecondLine, zipCode, city, null, country.toResponseDTO(), contact);
        }

        return new PaymentResponseDTO(id, name, lastname, document, documentType.getDescription(), address,
            addressSecondLine, zipCode, city, state.toResponseDTO(), country.toResponseDTO(), contact);
    }
}
