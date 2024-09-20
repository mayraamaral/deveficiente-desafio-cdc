package dev.mayra.seeddesafiocdc.model.coupon;

import java.time.LocalDate;

public class CouponResponseDTO {
    private Long id;
    private String code;
    private Integer percentage;
    private LocalDate expirationDate;

    public CouponResponseDTO(Long id, String code, Integer percentage, LocalDate expirationDate) {
        this.id = id;
        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
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
