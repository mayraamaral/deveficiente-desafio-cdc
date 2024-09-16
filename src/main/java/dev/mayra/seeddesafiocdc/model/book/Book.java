package dev.mayra.seeddesafiocdc.model.book;

import dev.mayra.seeddesafiocdc.model.author.Author;
import dev.mayra.seeddesafiocdc.model.author.AuthorMinifiedDTO;
import dev.mayra.seeddesafiocdc.model.category.Category;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(name = "abstract")
    private String bookAbstract;

    @Column
    private String summary;

    @Column
    private Double price;

    @Column(name = "pages_number")
    private Integer pagesNumber;

    @Column
    private String isbn;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "author_x_book",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @Deprecated
    public Book() {}

    public Book(String title, String bookAbstract, String summary, Double price, Integer pagesNumber, String isbn, LocalDate publishDate, Category category, List<Author> authors) {
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

    public Book(BookRequestDTO dto, Category category, List<Author> authors) {
        this.title = dto.getTitle();
        this.bookAbstract = dto.getBookAbstract();
        this.summary = dto.getSummary();
        this.price = dto.getPrice();
        this.pagesNumber = dto.getPagesNumber();
        this.isbn = dto.getIsbn();
        this.publishDate = dto.getPublishDate();
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

    public Integer getPagesNumber() {
        return pagesNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public BookResponseDTO toResponseDTO() {
        return new BookResponseDTO(id, title, bookAbstract, summary, price, pagesNumber,
            isbn, publishDate, category,
            authors.stream().map(AuthorMinifiedDTO::new).toList());
    }
}
