package dev.mayra.seeddesafiocdc.model.purchaseItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PurchaseItemRequestDTO {
    @NotNull(message = "Book id can't be null")
    private Long bookId;

    @NotNull(message = "Book quantity can't be null")
    @Min(value = 1, message = "Book quantity needs to be greater than zero")
    private Long quantity;

    public PurchaseItemRequestDTO(Long bookId, Long quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
