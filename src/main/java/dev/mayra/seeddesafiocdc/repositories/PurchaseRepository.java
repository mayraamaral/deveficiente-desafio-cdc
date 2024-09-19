package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("""
        SELECT p
        FROM Purchase p
        LEFT JOIN FETCH p.items i
    """)
    List<Purchase> findAllWithItems();
}
