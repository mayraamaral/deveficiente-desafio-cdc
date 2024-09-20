package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.purchase.Purchase;
import dev.mayra.seeddesafiocdc.model.purchase.PurchaseMinifiedProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("""
        SELECT p
        FROM Purchase p
        LEFT JOIN FETCH p.items i
    """)
    List<Purchase> findAllWithItems();

    @Query(value = """
        SELECT
            purchase_id AS id,
            subtotal,
            total,
            coupon_code
        FROM purchase
        WHERE purchase_id = :id
    """, nativeQuery = true)
    Optional<PurchaseMinifiedProjection> findByIdMinified(@Param("id") Long id);
}
