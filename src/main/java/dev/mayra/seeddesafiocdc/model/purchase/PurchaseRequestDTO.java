package dev.mayra.seeddesafiocdc.model.purchase;

import dev.mayra.seeddesafiocdc.model.purchaseItem.PurchaseItemRequestDTO;
import dev.mayra.seeddesafiocdc.utils.validators.DocumentValidador;
import dev.mayra.seeddesafiocdc.utils.validators.NotEmptyList;
import dev.mayra.seeddesafiocdc.utils.validators.ValidStateIfCountryHasStates;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@ValidStateIfCountryHasStates
public class PurchaseRequestDTO {

    @Schema(description = "Fill the customer name", required = true, example = "Mayra")
    @NotBlank(message = "Name can't be null or empty")
    private String name;

    @Schema(description = "Fill the customer last name", required = true, example = "Amaral")
    @NotBlank(message = "Lastname can't be null or empty")
    private String lastname;

    @Schema(description = "Fill the customer document", required = true, example = "123.123.123-12")
    @NotBlank(message = "Document can't be null or empty")
    @DocumentValidador(message = "Invalid document. It should be a valid CPF or CNPJ")
    private String document;

    @Schema(description = "Fill the customer document type", required = true, example = "CPF or CNPJ")
    @NotBlank(message = "Document type can't be null or empty")
    private String documentType;

    @Schema(description = "Fill the customer address", required = true, example = "Avenida Paulista")
    @NotBlank(message = "Address can't be null or empty")
    private String address;

    @Schema(description = "Fill the customer address second line", required = true, example = "Prédio")
    @NotBlank(message = "Second line of address can't be null or empty")
    private String addressSecondLine;

    @Schema(description = "Fill the customer zip code", required = true, example = "12312-123")
    @NotBlank(message = "Zip code can't be null or empty")
    private String zipCode;

    @Schema(description = "Fill the customer city", required = true, example = "São Paulo")
    @NotBlank(message = "City can't be null or empty")
    private String city;

    private Long stateId;

    @Schema(description = "Fill the customer country id", required = true, example = "1")
    @NotNull(message = "Country id can't be null or empty")
    private Long countryId;

    @Schema(description = "Fill the customer contact info", required = true, example = "83999990000")
    @NotBlank(message = "Contact can't be null or empty")
    private String contact;

    @NotNull(message = "A purchase needs to have at least one item")
    @NotEmptyList(message = "A purchase needs to have at least one item")
    private List<PurchaseItemRequestDTO> items;

    @Schema(description = "Fill the purchase total", required = true, example = "100")
    @NotNull(message = "Total can't be null")
    @Min(value = 1, message = "Total needs to greater than one")
    private Double total;

    @Schema(description = "Fill the coupon code", example = "verynicecode")
    private String couponCode;

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

    public Double getTotal() {
        return total;
    }

    public List<PurchaseItemRequestDTO> getItems() {
        return items;
    }

    public String getCouponCode() {
        return couponCode;
    }
}
