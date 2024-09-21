package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.controllers.docs.AuthorControllerDoc;
import dev.mayra.seeddesafiocdc.model.author.Author;
import dev.mayra.seeddesafiocdc.model.author.AuthorRequestDTO;
import dev.mayra.seeddesafiocdc.model.author.AuthorResponseDTO;
import dev.mayra.seeddesafiocdc.repositories.AuthorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Tag(name = "Author", description = "Operations related to the Author entity")
public class AuthorController implements AuthorControllerDoc {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> create(@RequestBody @Valid AuthorRequestDTO author) {
        AuthorResponseDTO created = authorRepository.save(new Author(author)).toResponseDTO();

        return ResponseEntity.ok().body(created);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> listAll() {
        return ResponseEntity.ok().body(authorRepository.findAll()
            .stream().map(Author::toResponseDTO)
            .toList());
    }
}
