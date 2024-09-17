package dev.mayra.seeddesafiocdc.model.book;

import dev.mayra.seeddesafiocdc.model.author.AuthorMinifiedDTO;
import dev.mayra.seeddesafiocdc.model.category.CategoryResponseDTO;

import java.time.LocalDate;
import java.util.List;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String bookAbstract;
    private String summary;
    private Double price;
    private Short pagesNumber;
    private String isbn;
    private LocalDate publishDate;
    private CategoryResponseDTO category;
    private List<AuthorMinifiedDTO> authors;

    public BookResponseDTO(Long id, String title, String bookAbstract, String summary, Double price, Short pagesNumber, String isbn, LocalDate publishDate, CategoryResponseDTO category, List<AuthorMinifiedDTO> authors) {
        this.id = id;
        this.title = title;
        this.bookAbstract = bookAbstract;
        this.summary = summary;
        this.price = price;
        this.pagesNumber = pagesNumber;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.category = category;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBookAbstract() {
        return bookAbstract;
    }

    public String getSummary() {
        return summary;
    }

    public Double getPrice() {
        return price;
    }

    public Short getPagesNumber() {
        return pagesNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public List<AuthorMinifiedDTO> getAuthors() {
        return authors;
    }
}
