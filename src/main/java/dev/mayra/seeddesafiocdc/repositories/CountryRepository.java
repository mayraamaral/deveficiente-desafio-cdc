package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.country.CountryWithStatesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("""
        SELECT c
        FROM Country c
        LEFT JOIN FETCH c.states s
    """)
    List<CountryWithStatesProjection> findAllWithStates();
}
