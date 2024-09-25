package dev.mayra.seeddesafiocdc.model.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.mayra.seeddesafiocdc.utils.validators.FutureString;
import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class CouponRequestDTO {

    @NotBlank(message = "Coupon code can't be null or empty")
    @UniqueValue(fieldName = "code", domainClass = Coupon.class, message = "Coupon code already in use")
    private String code;

    @Min(value = 1, message = "Percentage needs to be at least 1%")
    @Max(value = 100, message = "Percentage needs to be lower or equal to 100%")
    @Positive(message = "Percentage needs to be a positive number")
    private Integer percentage;

    @FutureString(message = "Expiration date needs to be in the future")
    @Schema(description = "Fill the publish date", required = true, example = "31-12-2024", format = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String expirationDate;

    public CouponRequestDTO(String code, Integer percentage, String expirationDate) {
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

    public String getExpirationDate() {
        return expirationDate;
    }
}
