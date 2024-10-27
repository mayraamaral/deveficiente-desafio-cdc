package dev.mayra.seeddesafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.model.state.StateRequestDTO;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
import dev.mayra.seeddesafiocdc.repositories.StateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StateController.class)
class StateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private StateRepository stateRepository;
    @MockBean
    private CountryRepository countryRepository;

    private Country country;
    private StateRequestDTO dto;
    private State entity;

    @BeforeEach
    void setUp() {
        country = new Country(1L, "Brasil");
        dto = new StateRequestDTO("Paraíba", country.getId());
        entity = new State(dto, country);

        TypedQuery<Boolean> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(false);
    }

    @Test
    void create__should_create_state_with_success() throws Exception {
        when(countryRepository.findById(country.getId())).thenReturn(Optional.of(country));
        when(stateRepository.save(any(State.class))).thenReturn(entity);

        mockMvc.perform(post("/state")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(dto.getName()))
            .andExpect(jsonPath("$.country.name").value(country.getName()));
    }

    @Test
    void listAll__should_list_all_states_with_success() throws Exception {
        List<State> states = List.of(entity);

        when(stateRepository.findAll()).thenReturn(states);

        mockMvc.perform(get("/state")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(dto.getName()))
            .andExpect(jsonPath("$[0].country.name").value(country.getName()));
    }

    @Test
    void listAllByCountryId__should_list_all_states_by_country_with_success() throws Exception {
        String idString = String.valueOf(country.getId());
        List<State> states = List.of(entity);

        when(stateRepository.findAllByCountry_Id(country.getId())).thenReturn(states);

        mockMvc.perform(get("/state/".concat(idString), country.getId())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(dto.getName()))
            .andExpect(jsonPath("$[0].country.name").value(country.getName()));
    }

    @Test
    void listAllByCountryId__should_return_empty_list_when_country_not_found() throws Exception {
        Long randomId = new Random().nextLong();
        String randomIdString = String.valueOf(new Random().nextLong());

        when(stateRepository.findAllByCountry_Id(randomId)).thenReturn(List.of());

        mockMvc.perform(get("/state/".concat(randomIdString), 3244)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }
}
