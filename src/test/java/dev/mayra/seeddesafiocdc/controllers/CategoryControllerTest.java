package dev.mayra.seeddesafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mayra.seeddesafiocdc.model.category.Category;
import dev.mayra.seeddesafiocdc.model.category.CategoryRequestDTO;
import dev.mayra.seeddesafiocdc.repositories.CategoryRepository;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private CategoryRepository categoryRepository;

    private CategoryRequestDTO dto;
    private Category entity;

    @BeforeEach
    void setUp() {
        dto = new CategoryRequestDTO("Cool name");
        entity = new Category(dto);

        TypedQuery<Boolean> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(false);
    }

    @Test
    void create__should_create_category_with_success() throws Exception {
        when(categoryRepository.save(any(Category.class))).thenReturn(entity);

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(dto.getName()));
    }

    @Test
    void create__should_throw_exception_when_category_dont_have_a_name() throws Exception {
        CategoryRequestDTO invalidDto = new CategoryRequestDTO("");

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors.name").value("Name can't be null or empty"));
    }

    @Test
    void findAll() throws Exception {
        List<Category> categories = List.of(entity);

        when(categoryRepository.findAll()).thenReturn(categories);

        mockMvc.perform(get("/category")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value(dto.getName()));
    }
}