package dev.mayra.seeddesafiocdc.model.coupon;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Coupon {

    @Id
    @Column(name = "coupon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private Integer percentage;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Deprecated
    public Coupon() {}

    public Coupon(Long id, String code, Integer percentage, LocalDate expirationDate) {
        this.id = id;
        this.code = code;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public Coupon(CouponRequestDTO dto) {
        this.code = dto.getCode();
        this.percentage = dto.getPercentage();
        this.expirationDate = dto.getExpirationDate();
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

    public CouponResponseDTO toResponseDTO() {
        return new CouponResponseDTO(id, code, percentage, expirationDate);
    }
}
