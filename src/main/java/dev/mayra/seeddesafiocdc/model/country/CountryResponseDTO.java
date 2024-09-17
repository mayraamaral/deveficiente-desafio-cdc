package dev.mayra.seeddesafiocdc.model.country;

public class CountryResponseDTO {
    private Long id;
    private String name;

    public CountryResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
