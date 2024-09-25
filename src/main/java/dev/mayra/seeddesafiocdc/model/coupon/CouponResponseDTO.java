package dev.mayra.seeddesafiocdc.model.coupon;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CouponResponseDTO {
    private String code;
    private Integer percentage;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;

    public CouponResponseDTO(String code, Integer percentage, LocalDate expirationDate) {
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
