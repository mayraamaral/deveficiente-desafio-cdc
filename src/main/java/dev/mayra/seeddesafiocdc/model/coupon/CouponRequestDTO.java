package dev.mayra.seeddesafiocdc.model.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.mayra.seeddesafiocdc.utils.validators.FutureString;
import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class CouponRequestDTO {

    @Schema(description = "Fill the coupon code", required = true, example = "verynicecode")
    @NotBlank(message = "Coupon code can't be null or empty")
    @UniqueValue(fieldName = "code", domainClass = Coupon.class, message = "Coupon code already in use")
    private String code;

    @Schema(description = "Fill the percentage", required = true, example = "50")
    @Min(value = 1, message = "Percentage needs to be at least 1")
    @Max(value = 100, message = "Percentage needs to be lower or equal to 100")
    @NotNull(message = "Percentage can't be null")
    private Integer percentage;

    @Schema(description = "Fill the espiration date", required = true, example = "31-12-2024", format = "dd-MM-yyyy")
    @NotNull(message = "Expiration date can't be null")
    @FutureString(message = "Expiration date needs to be in the future")
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
