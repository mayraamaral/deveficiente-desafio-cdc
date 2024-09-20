package dev.mayra.seeddesafiocdc.model.coupon;

import dev.mayra.seeddesafiocdc.model.validation.ValidationResult;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Coupon {

    @Id
    @Column
    private String code;

    @Column
    private Integer percentage;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Deprecated
    public Coupon() {}

    public Coupon(String code, Integer percentage, LocalDate expirationDate) {
        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public Coupon(CouponRequestDTO dto) {
        this.code = dto.getCode();
        this.percentage = dto.getPercentage();
        this.expirationDate = dto.getExpirationDate();
    }

    public boolean isValid() {
        return expirationDate.isAfter(LocalDate.now());
    }

    public ValidationResult validate() {
        if(!isValid()) {
            return ValidationResult.invalid("Coupon is expired");
        }

        return ValidationResult.valid();
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

    public CouponResponseDTO toResponseDTO() {
        return new CouponResponseDTO(code, percentage, expirationDate);
    }
}
