package dev.mayra.seeddesafiocdc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mayra.seeddesafiocdc.model.author.Author;
import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.book.BookMinifiedProjection;
import dev.mayra.seeddesafiocdc.model.book.BookRequestDTO;
import dev.mayra.seeddesafiocdc.model.category.Category;
import dev.mayra.seeddesafiocdc.repositories.AuthorRepository;
import dev.mayra.seeddesafiocdc.repositories.BookRepository;
import dev.mayra.seeddesafiocdc.repositories.CategoryRepository;
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
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private AuthorRepository authorRepository;

    private BookRequestDTO dto;
    private Book entity;
    private Category category;
    private Author author;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "Business"); // ID definido pelo construtor de teste
        author = new Author(1L, "Michael Scott", "michael.scott@dundermifflin.com", "Regional Manager at Dunder Mifflin");

        dto = new BookRequestDTO(
            "Somehow I Manage",
            "A humorous take on management tips and office life.",
            "A deeper look into the life and lessons of Michael Scott, Regional Manager.",
            50.0,
            (short) 250,
            "978-1234567890",
            "01-01-2025",
            1L,
            List.of(1L)
        );

        entity = new Book(dto, category, List.of(author));

        TypedQuery<Boolean> mockQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), any(Class.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(false);
    }

    @Test
    void create__should_create_book_with_success() throws Exception {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(authorRepository.findAllById(any())).thenReturn(List.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(entity);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value(dto.getTitle()))
            .andExpect(jsonPath("$.price").value(dto.getPrice()))
            .andExpect(jsonPath("$.publishDate").value("2025-01-01"))
            .andExpect(jsonPath("$.category.name").value(category.getName()))
            .andExpect(jsonPath("$.authors[0].name").value(author.getName()));
    }

    @ParameterizedTest
    @MethodSource("invalidBookRequestProvider")
    void create__should_throw_exception_for_invalid_fields(BookRequestDTO invalidDto, String jsonPath, String errorMessage) throws Exception {

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath(jsonPath).value(errorMessage));
    }

    @Test
    void create__should_throw_exception_when_category_not_found() throws Exception {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errors.message").value("Category not found"));
    }

    @Test
    void create__should_throw_exception_when_authors_not_found() throws Exception {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(authorRepository.findAllById(any())).thenReturn(List.of());

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isNotFound())
            .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
            .andExpect(jsonPath("$.errors.message").value("No author found"));
    }

    @Test
    void listAll__should_list_all_books_with_success() throws Exception {
        when(bookRepository.findAll()).thenReturn(List.of(entity));

        mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value(dto.getTitle()));
    }

    @Test
    void listAllMinified__should_list_all_books_minified_with_success() throws Exception {
        BookMinifiedProjection minifiedBook = new BookMinifiedProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getTitle() {
                return "Somehow I Manage";
            }
        };

        when(bookRepository.findAllMinified()).thenReturn(List.of(minifiedBook));

        mockMvc.perform(get("/book/minified")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value(dto.getTitle()));
    }

    @Test
    void listById__should_return_book_by_id_with_success() throws Exception {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        mockMvc.perform(get("/book/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value(dto.getTitle()));
    }

    @Test
    void listById__should_throw_exception_when_book_not_found() throws Exception {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/book/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errors.message").value("Book not found"));
    }

    private static Stream<Arguments> invalidBookRequestProvider() {
        return Stream.of(
            Arguments.of(
                new BookRequestDTO("", "Abstract", "Summary", 50.0, (short) 250, "978-1234567890", "01-01-2025", 1L, List.of(1L)),
                "$.errors.title",
                "Title can't be null or empty"
            ),
            Arguments.of(
                new BookRequestDTO("Somehow I Manage", "", "Summary", 50.0, (short) 250, "978-1234567890", "01-01-2025", 1L, List.of(1L)),
                "$.errors.bookAbstract",
                "Abstract can't be null or empty"
            ),
            Arguments.of(
                new BookRequestDTO("Somehow I Manage", "Abstract", "Summary", null, (short) 250, "978-1234567890", "01-01-2025", 1L, List.of(1L)),
                "$.errors.price",
                "Price can't be null"
            ),
            Arguments.of(
                new BookRequestDTO("Somehow I Manage", "Abstract", "Summary", 15.0, (short) 250, "978-1234567890", "01-01-2025", 1L, List.of(1L)),
                "$.errors.price",
                "Book needs to have price greater or equal to 20"
            ),
            Arguments.of(
                new BookRequestDTO("Somehow I Manage", "Abstract", "Summary", 50.0, null, "978-1234567890", "01-01-2025", 1L, List.of(1L)),
                "$.errors.pagesNumber",
                "Pages number can't be null"
            ),
            Arguments.of(
                new BookRequestDTO("Somehow I Manage", "Abstract", "Summary", 50.0, (short) 250, "", "01-01-2025", 1L, List.of(1L)),
                "$.errors.isbn",
                "ISBN can't be null or empty"
            ),
            Arguments.of(
                new BookRequestDTO("Somehow I Manage", "Abstract", "Summary", 50.0, (short) 250, "978-1234567890", "", 1L, List.of(1L)),
                "$.errors.publishDate",
                "Publish date can't be null or empty"
            )
        );
    }
}
