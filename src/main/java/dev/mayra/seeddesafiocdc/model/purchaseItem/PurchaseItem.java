package dev.mayra.seeddesafiocdc.model.purchaseItem;

import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.purchase.Purchase;
import jakarta.persistence.*;

@Entity(name = "purchase_item")
public class PurchaseItem {

    @Id
    @Column(name = "purchase_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Deprecated
    public PurchaseItem() {}

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
