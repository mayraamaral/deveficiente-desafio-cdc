package dev.mayra.seeddesafiocdc.model.author;

import java.time.LocalDate;

public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String description;
    private LocalDate createdAt;

    public AuthorResponseDTO(Long id, String name, String email, String description, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
