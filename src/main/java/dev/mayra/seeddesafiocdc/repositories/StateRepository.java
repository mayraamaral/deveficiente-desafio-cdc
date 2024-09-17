package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.state.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findAllByCountry_Id(Long countryId);
}
