package dev.mayra.seeddesafiocdc.model.author;

public class AuthorMinifiedDTO {
    private Long id;
    private String name;
    private String email;

    public AuthorMinifiedDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public AuthorMinifiedDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
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
}
