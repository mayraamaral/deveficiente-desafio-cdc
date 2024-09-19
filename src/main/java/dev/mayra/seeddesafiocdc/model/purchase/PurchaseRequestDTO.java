package dev.mayra.seeddesafiocdc.model.purchase;

import dev.mayra.seeddesafiocdc.utils.validators.ValidStateIfCountryHasStates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@ValidStateIfCountryHasStates
public class PurchaseRequestDTO {
    @NotBlank(message = "Name can't be null or empty")
    private String name;

    @NotBlank(message = "Lastname can't be null or empty")
    private String lastname;

    @NotBlank(message = "Document can't be null or empty")
    @Pattern(
        regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)|(^\\d{11}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$)|(^\\d{14}$)",
        message = "Invalid document type. It should be a valid CPF or CNPJ"
    )
    private String document;

    @NotBlank(message = "Document type can't be null or empty")
    private String documentType;

    @NotBlank(message = "Address can't be null or empty")
    private String address;

    @NotBlank(message = "Second line of address can't be null or empty")
    private String addressSecondLine;

    @NotBlank(message = "Zip code can't be null or empty")
    private String zipCode;

    @NotBlank(message = "City can't be null or empty")
    private String city;

    private Long stateId;

    @NotNull(message = "Country id can't be null or empty")
    private Long countryId;

    @NotBlank(message = "Contact can't be null or empty")
    private String contact;

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

    public Long getStateId() {
        return stateId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getContact() {
        return contact;
    }
}