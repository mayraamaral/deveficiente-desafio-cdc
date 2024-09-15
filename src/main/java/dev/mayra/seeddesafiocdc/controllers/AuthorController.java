package dev.mayra.seeddesafiocdc.controllers;

import dev.mayra.seeddesafiocdc.model.author.Author;
import dev.mayra.seeddesafiocdc.model.author.AuthorRequestDTO;
import dev.mayra.seeddesafiocdc.model.author.AuthorResponseDTO;
import dev.mayra.seeddesafiocdc.repositories.AuthorRepository;
import dev.mayra.seeddesafiocdc.utils.exceptions.AlreadyInUseException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
@Tag(name = "Author", description = "Operations related to the Author entity")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> create(@RequestBody @Valid AuthorRequestDTO author) {
        if(authorRepository.existsByEmail(author.getEmail())) {
            throw new AlreadyInUseException("E-mail already in use");
        }

        AuthorResponseDTO created = authorRepository.save(new Author(author)).toResponseDTO();

        return ResponseEntity.ok().body(created);
    }
}
