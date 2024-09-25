package dev.mayra.seeddesafiocdc.model.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.mayra.seeddesafiocdc.utils.validators.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookRequestDTO {

    @Schema(description = "Fill the book title", required = true, example = "Olhai os lírios do campo")
    @NotBlank(message = "Title can't be null or empty")
    @UniqueValue(fieldName = "title", domainClass = Book.class, message = "Title already in use")
    private String title;

    @Schema(description = "Fill the book abstract", required = true, example = "Fala sobre a jornada de um " +
        "jovem pobre que ascende socialmente")
    @NotBlank(message = "Abstract can't be null or empty")
    private String bookAbstract;

    @Schema(description = "Fill the book summary", required = true, example = "Apesar de desejar ascender socialmente" +
        "desde muito novo, quando conquista o que buscava, ainda sente uma sensação de vazio. O livro" +
        "trata sobre problemas sociais e econômicos, sobre arrogância e ganância, mas" +
        "acima de tudo, sobre a visão que temos de nós mesmos.")
    private String summary;

    @Schema(description = "Fill the book price", required = true, example = "50")
    @NotNull(message = "Price can't be null")
    @Min(value = 20, message = "Book needs to have price greater or equal to 20")
    private Double price;

    @Schema(description = "Fill the book number of pages", required = true, example = "288")
    @NotNull(message = "Pages number can't be null")
    private Short pagesNumber;

    @Schema(description = "Fill the book ISBN", required = true, example = "978-8535906097")
    @NotBlank(message = "ISBN can't be null or empty")
    @UniqueValue(fieldName = "isbn", domainClass = Book.class, message = "ISBN already in use")
    private String isbn;

    @Schema(description = "Fill the publish date", required = true, example = "01-01-1938", format = "dd-MM-yyyy")
    @NotBlank(message = "Publish date can't be null or empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String publishDate;

    @Schema(description = "Fill the book category id", required = true, example = "1")
    @NotNull(message = "Category Id can't be null")
    private Long categoryId;

    @Schema(description = "Fill the book authors id list", required = true, example = "[1, 2]")
    @NotNull(message = "Authors can't be null")
    private List<Long> authorsIds;

    public BookRequestDTO(String title, String bookAbstract, String summary, Double price, Short pagesNumber, String isbn, String publishDate, Long categoryId, List<Long> authorsIds) {
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

    public String getPublishDate() {
        return publishDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public List<Long> getAuthorsIds() {
        return authorsIds;
    }
}
