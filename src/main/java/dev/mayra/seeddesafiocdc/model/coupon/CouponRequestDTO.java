package dev.mayra.seeddesafiocdc.model.coupon;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CouponRequestDTO {

    @NotBlank(message = "Coupon code can't be null or empty")
    @UniqueValue(fieldName = "code", domainClass = Coupon.class, message = "Coupon code already in use")
    private String code;

    @Min(value = 1, message = "Percentage needs to be at least 1%")
    @Max(value = 100, message = "Percentage needs to be lower or equal to 100%")
    @Positive(message = "Percentage needs to be a positive number")
    private Integer percentage;

    @Future(message = "Expiration date needs to be in the future")
    private LocalDate expirationDate;

    public CouponRequestDTO(String code, Integer percentage, LocalDate expirationDate) {
        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
