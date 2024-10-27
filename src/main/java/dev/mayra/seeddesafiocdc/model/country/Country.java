package dev.mayra.seeddesafiocdc.model.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.mayra.seeddesafiocdc.model.state.State;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<State> states;

    @Deprecated
    public Country() {}

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country(CountryRequestDTO dto) {
        this.name = dto.getName();
    }

    public boolean hasStates() {
        return !states.isEmpty();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public CountryResponseDTO toResponseDTO() {
        return new CountryResponseDTO(id, name);
    }

    public CountryWithStatesResponseDTO toResponseDTOWithStates() {
        return new CountryWithStatesResponseDTO(id, name,
            states.stream().map(State::toResponseDTO).toList());
    }
}
