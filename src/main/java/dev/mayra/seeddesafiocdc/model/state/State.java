package dev.mayra.seeddesafiocdc.model.state;

import dev.mayra.seeddesafiocdc.model.country.Country;
import jakarta.persistence.*;

@Entity
public class State {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Deprecated
    public State() {}

    public State(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public State(StateRequestDTO dto, Country country) {
        this.name = dto.getName();
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public StateWithCountryResponseDTO toWithStateResponseDTO() {
        return new StateWithCountryResponseDTO(id, name, country.toResponseDTO());
    }

    public StateResponseDTO toResponseDTO() {
        return new StateResponseDTO(id, name);
    }
}
