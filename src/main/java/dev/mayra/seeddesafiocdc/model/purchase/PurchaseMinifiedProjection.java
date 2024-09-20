package dev.mayra.seeddesafiocdc.model.purchase;

import java.util.Optional;

public interface PurchaseMinifiedProjection {
    Long getId();
    Double getSubtotal();
    Double getTotal();
    String getCouponCode();

    public default boolean  hasCoupon() {
        return Optional.ofNullable(getCouponCode()).isPresent();
    }
}
