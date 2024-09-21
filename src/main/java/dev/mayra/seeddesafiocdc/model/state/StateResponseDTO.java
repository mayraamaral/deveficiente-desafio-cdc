package dev.mayra.seeddesafiocdc.model.state;

public class StateResponseDTO {
    private Long id;
    private String name;

    public StateResponseDTO(Long id, String name) {
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
