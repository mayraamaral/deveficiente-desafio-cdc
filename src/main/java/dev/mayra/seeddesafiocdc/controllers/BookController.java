package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.author.Author;
import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.book.BookMinifiedDTO;
import dev.mayra.seeddesafiocdc.model.book.BookRequestDTO;
import dev.mayra.seeddesafiocdc.model.book.BookResponseDTO;
import dev.mayra.seeddesafiocdc.model.category.Category;
import dev.mayra.seeddesafiocdc.repositories.AuthorRepository;
import dev.mayra.seeddesafiocdc.repositories.BookRepository;
import dev.mayra.seeddesafiocdc.repositories.CategoryRepository;
import dev.mayra.seeddesafiocdc.utils.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Operations related to the Book entity")
public class BookController {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    private BookController(BookRepository bookRepository, CategoryRepository categoryRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody @Valid BookRequestDTO book) {
        Category category = categoryRepository.findById(book.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Category not found"));

        List<Author> authors = findAuthorsByIds(book.getAuthorsIds());

        BookResponseDTO created = bookRepository.save(new Book(book, category, authors))
            .toResponseDTO();

        return ResponseEntity.ok().body(created);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> listAll() {
        return ResponseEntity.ok().body(bookRepository.findAll()
            .stream().map(Book::toResponseDTO)
            .toList());
    }

    @GetMapping("/minified")
    public ResponseEntity<List<BookMinifiedDTO>> listAllMinified() {
        return ResponseEntity.ok().body(bookRepository.findAll()
            .stream().map(Book::toMinifiedDTO)
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> listById(@PathVariable Long id) {
        Book found = bookRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Book not found"));

        return ResponseEntity.ok().body(found.toResponseDTO());
    }

    private List<Author> findAuthorsByIds(List<Long> authorIds) {
        List<Author> authors = authorRepository.findAllById(authorIds);

        if(authors.isEmpty()) {
            throw new NotFoundException("No author found");
        }

        List<Long> foundAuthorIds = authors.stream().map(Author::getId).toList();
        List<Long> missingAuthorIds = authorIds.stream().filter(id -> !foundAuthorIds.contains(id)).toList();

        if (!missingAuthorIds.isEmpty()) {
            throw new NotFoundException("Authors not found with ids: " + missingAuthorIds);
        }

        return authors;
    }
}
