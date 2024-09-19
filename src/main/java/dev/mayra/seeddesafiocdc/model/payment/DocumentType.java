package dev.mayra.seeddesafiocdc.model.payment;

public enum DocumentType {
    CPF("CPF"),
    CNPJ("CNPJ");

    private String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
