package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}