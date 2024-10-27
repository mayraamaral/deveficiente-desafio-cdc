package dev.mayra.seeddesafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mayra.seeddesafiocdc.model.author.Author;
import dev.mayra.seeddesafiocdc.model.author.AuthorRequestDTO;
import dev.mayra.seeddesafiocdc.repositories.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private AuthorRepository authorRepository;

    private AuthorRequestDTO dto;
    private Author entity;

    @BeforeEach
    void setUp() {
        dto = new AuthorRequestDTO(
            "Michael Scott",
            "michael.scott@dundermiflin.com",
            "Author of Somehow I Manage");

        entity = new Author(dto);

        TypedQuery<Boolean> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(false);
    }

    @Test
    void create__should_create_author_with_success() throws Exception {
        when(authorRepository.save(any(Author.class))).thenReturn(entity);

        mockMvc.perform(post("/author")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(dto.getName()));
    }

    @ParameterizedTest
    @MethodSource("invalidAuthorRequestDTOProvider")
    void create__should_throw_exception_for_invalid_fields(AuthorRequestDTO invalidDto, String jsonPath, String errorMessage) throws Exception {

        mockMvc.perform(post("/author")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(jsonPath)
                .value(errorMessage));
    }

    @Test
    void listAll__should_list_all_authors_with_success() throws Exception {
        List<Author> authors = List.of(entity);

        when(authorRepository.findAll()).thenReturn(authors);

        mockMvc.perform(get("/author")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(dto.getName()));
    }

    private static Stream<Arguments> invalidAuthorRequestDTOProvider() {
        return Stream.of(
            Arguments.of(
                new AuthorRequestDTO(
                    "",
                    "michael.scott@dundermiflin.com",
                    "Author of Somehow I Manage"
                ),
                "$.errors.name",
                "Name can't be null or empty"
            ),
            Arguments.of(
                new AuthorRequestDTO(
                    "Michael Scott",
                    "",
                    "Author of Somehow I Manage"
                ),
                "$.errors.email",
                "E-mail can't be null or empty"
            ),
            Arguments.of(
                new AuthorRequestDTO(
                    "Michael Scott",
                    "michael.scott@dundermiflin.com",
                    ""
                ),
                "$.errors.description",
                "Description can't be null or empty"
            )
        );
    }
}