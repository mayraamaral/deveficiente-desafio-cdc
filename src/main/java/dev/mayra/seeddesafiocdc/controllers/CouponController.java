package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.coupon.Coupon;
import dev.mayra.seeddesafiocdc.model.coupon.CouponRequestDTO;
import dev.mayra.seeddesafiocdc.model.coupon.CouponResponseDTO;
import dev.mayra.seeddesafiocdc.repositories.CouponRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
@Tag(name = "Coupon", description = "Operations related to the Coupon entity")
public class CouponController {
    private final CouponRepository couponRepository;

    public CouponController(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @PostMapping
    public ResponseEntity<CouponResponseDTO> create(@RequestBody @Valid CouponRequestDTO coupon) {
        Coupon saved = couponRepository.save(new Coupon(coupon));

        return ResponseEntity.ok().body(saved.toResponseDTO());
    }

    @GetMapping
    public ResponseEntity<List<CouponResponseDTO>> listAll() {
        return ResponseEntity.ok().body(couponRepository.findAll()
            .stream().map(Coupon::toResponseDTO).toList());
    }
}
