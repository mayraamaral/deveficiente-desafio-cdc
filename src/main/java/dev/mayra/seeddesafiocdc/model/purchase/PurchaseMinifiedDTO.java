package dev.mayra.seeddesafiocdc.model.purchase;

public class PurchaseMinifiedDTO {
    private Long id;
    private Double subtotal;
    private Double total;
    private Boolean hasCoupon;
    private String couponCode;

    public PurchaseMinifiedDTO(Long id, Double subtotal, Double total, Boolean hasCoupon, String couponCode) {
        this.id = id;
        this.subtotal = subtotal;
        this.total = total;
        this.hasCoupon = hasCoupon;
        this.couponCode = couponCode;
    }

    public PurchaseMinifiedDTO(PurchaseMinifiedProjection projection) {
        this.id = projection.getId();
        this.subtotal = projection.getSubtotal();
        this.total = projection.getTotal();
        this.couponCode = projection.getCouponCode();
        this.hasCoupon = projection.hasCoupon();
    }

    public Long getId() {
        return id;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public Boolean getHasCoupon() {
        return hasCoupon;
    }

    public String getCouponCode() {
        return couponCode;
    }
}
