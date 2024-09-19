package dev.mayra.seeddesafiocdc.model.purchaseItem;

import dev.mayra.seeddesafiocdc.model.book.BookMinifiedDTO;
import dev.mayra.seeddesafiocdc.model.book.BookResponseDTO;

public class PurchaseItemResponseDTO {
    private Long id;
    private BookMinifiedDTO book;
    private Long quantity;

    public PurchaseItemResponseDTO(Long id, BookMinifiedDTO book, Long quantity) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public BookMinifiedDTO getBook() {
        return book;
    }

    public Long getQuantity() {
        return quantity;
    }
}
