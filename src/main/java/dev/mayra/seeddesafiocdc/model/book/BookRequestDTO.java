package dev.mayra.seeddesafiocdc.model.book;

import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class BookRequestDTO {
    @NotBlank(message = "Title can't be null or empty")
    @UniqueValue(fieldName = "title", domainClass = Book.class, message = "Title already in use")
    @Schema(description = "Fill the book title", required = true, example = "Olhai os lírios do campo")
    private String title;

    @NotBlank(message = "Abstract can't be null or empty")
    @Schema(description = "Fill the book abstract", required = true, example = "Fala sobre a jornada de um " +
        "jovem pobre que ascende socialmente")
    private String bookAbstract;

    @Schema(description = "Fill the book summary", required = true, example = "Apesar de desejar ascender socialmente" +
        "desde muito novo, quando conquista o que buscava, ainda sente uma sensação de vazio. O livro" +
        "trata sobre problemas sociais e econômicos, sobre arrogância e ganância, mas" +
        "acima de tudo, sobre a visão que temos de nós mesmos.")
    private String summary;

    @NotNull(message = "Price can't be null")
    @Min(value = 20, message = "Book needs to have price greater or equal to 20")
    @Schema(description = "Fill the book price", required = true, example = "50")
    private Double price;

    @NotNull(message = "Pages number can't be null")
    @Schema(description = "Fill the book number of pages", required = true, example = "288")
    private Short pagesNumber;

    @NotBlank(message = "ISBN can't be null or empty")
    @UniqueValue(fieldName = "isbn", domainClass = Book.class, message = "ISBN already in use")
    @Schema(description = "Fill the book ISBN", required = true, example = "978-8535906097")
    private String isbn;

    @NotNull(message = "Publish Date can't be null")
    @Schema(description = "Fill the publish date", required = true, example = "1938-01-01")
    private LocalDate publishDate;

    @NotNull(message = "Category Id can't be null")
    @Schema(description = "Fill the book category id", required = true, example = "1")
    private Long categoryId;

    @NotNull(message = "Authors can't be null")
    @Schema(description = "Fill the book authors id list", required = true, example = "[1, 2]")
    private List<Long> authorsIds;

    public BookRequestDTO(String title, String bookAbstract, String summary, Double price, Short pagesNumber, String isbn, LocalDate publishDate, Long categoryId, List<Long> authorsIds) {
        this.title = title;
        this.bookAbstract = bookAbstract;
        this.summary = summary;
        this.price = price;
        this.pagesNumber = pagesNumber;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.authorsIds = authorsIds;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public List<Long> getAuthorsIds() {
        return authorsIds;
    }
}
