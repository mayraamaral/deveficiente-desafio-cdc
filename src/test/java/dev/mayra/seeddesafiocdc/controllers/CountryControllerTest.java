package dev.mayra.seeddesafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mayra.seeddesafiocdc.model.country.Country;
import dev.mayra.seeddesafiocdc.model.country.CountryRequestDTO;
import dev.mayra.seeddesafiocdc.model.country.CountryWithStatesResponseDTO;
import dev.mayra.seeddesafiocdc.model.state.State;
import dev.mayra.seeddesafiocdc.repositories.CountryRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private CountryRepository countryRepository;

    private CountryRequestDTO dto;
    private Country entity;

    @BeforeEach
    void setUp() {
        dto = new CountryRequestDTO("Brasil");
        entity = new Country(dto);

        TypedQuery<Boolean> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(false);
    }

    @Test
    void create__should_create_country_with_success() throws Exception {
        when(countryRepository.save(any(Country.class))).thenReturn(entity);

        mockMvc.perform(post("/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(dto.getName()));
    }

    @Test
    void create__should_throw_exception_when_country_name_is_empty() throws Exception {
        CountryRequestDTO invalidDto = new CountryRequestDTO("");

        mockMvc.perform(post("/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.name").value("Name can't be null or empty"));
    }

    @Test
    void create__should_throw_exception_when_country_already_exists() throws Exception {
        when(countryRepository.save(any(Country.class))).thenThrow(new IllegalArgumentException("Country already exists"));

        mockMvc.perform(post("/country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.name").value("Country already exists"));
    }

    @Test
    void listAll__should_list_all_countries_with_success() throws Exception {
        List<Country> countries = List.of(entity);

        when(countryRepository.findAll()).thenReturn(countries);

        mockMvc.perform(get("/country")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(dto.getName()));
    }

    @Test
    void listAllWithStates__should_list_all_countries_with_states_with_success() throws Exception {
        State state = new State(1L, "Paraíba", entity);
        entity.setStates(List.of(state));

        when(countryRepository.findAllWithStates()).thenReturn(List.of(entity));

        mockMvc.perform(get("/country/with-states")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(dto.getName()))
            .andExpect(jsonPath("$[0].states").isArray());
    }
}
