package dev.mayra.seeddesafiocdc.model.author;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthorRequestDTO {

    @NotNull(message = "Name can't be null")
    private String name;

    @NotNull(message = "E-mail can't be null")
    @Email(message = "E-mail must be valid")
    @UniqueValue(fieldName = "email", domainClass = Author.class, message = "E-mail already in use")
    private String email;

    @NotNull(message = "Description can't be null")
    @Size(max = 400, message = "Description can have a maximum of 400 characters")
    private String description;

    public AuthorRequestDTO(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
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
