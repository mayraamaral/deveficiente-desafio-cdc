package dev.mayra.seeddesafiocdc.model.purchaseItem;

import dev.mayra.seeddesafiocdc.model.book.Book;

public class PurchaseItem {
    private Long id;
    private Book book;
    private Long quantity;

    public PurchaseItem(Book book, Long quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public PurchaseItem(PurchaseItemRequestDTO dto, Book book) {
        this.book = book;
        this.quantity = dto.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Long getQuantity() {
        return quantity;
    }

    public PurchaseItemResponseDTO toResponseDTO() {
        return new PurchaseItemResponseDTO(id, book.toMinifiedDTO(), quantity);
    }
}
