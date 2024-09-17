package dev.mayra.seeddesafiocdc.model.author;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthorRequestDTO {

    @NotBlank(message = "Name can't be null or empty")
    @Schema(description = "Fill the author name", required = true, example = "Érico Veríssimo")
    private String name;

    @NotBlank(message = "E-mail can't be null or empty")
    @Email(message = "E-mail must be valid")
    @UniqueValue(fieldName = "email", domainClass = Author.class, message = "E-mail already in use")
    @Schema(description = "Fill the author e-mail", required = true, example = "example@email.com")
    private String email;

    @NotBlank(message = "Description can't be null or empty")
    @Size(max = 400, message = "Description can have a maximum of 400 characters")
    @Schema(description = "Fill the author description", required = true, example = "Erico Lopes Verissimo foi um " +
        "escritor brasileiro. Com uma prosa simples e de fácil leitura, tornou-se um dos escritores mais " +
        "populares da literatura brasileira.")
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
