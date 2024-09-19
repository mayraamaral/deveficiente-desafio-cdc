package dev.mayra.seeddesafiocdc.model.purchase;

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
