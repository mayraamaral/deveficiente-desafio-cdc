package dev.mayra.seeddesafiocdc.model.book;

public class BookMinifiedDTO {
    private Long id;
    private String title;

    @Deprecated
    public BookMinifiedDTO() {}

    public BookMinifiedDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
