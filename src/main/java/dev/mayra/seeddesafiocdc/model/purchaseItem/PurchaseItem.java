package dev.mayra.seeddesafiocdc.model.purchaseItem;

import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.purchase.Purchase;

public class PurchaseItem {
    private Long id;
    private Book book;
    private Long quantity;
    private Purchase purchase;

    public PurchaseItem(Book book, Long quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public PurchaseItem(PurchaseItemRequestDTO dto, Book book, Purchase purchase) {
        this.book = book;
        this.quantity = dto.getQuantity();
        this.purchase = purchase;
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

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public PurchaseItemResponseDTO toResponseDTO() {
        return new PurchaseItemResponseDTO(id, book.toMinifiedDTO(), quantity);
    }
}
