CREATE TABLE purchase (
    purchase_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    document VARCHAR(100) NOT NULL,
    document_type VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    address_second_line VARCHAR(100) NOT NULL,
    zip_code VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state_id BIGINT,
    country_id BIGINT NOT NULL,
    contact VARCHAR(100) NOT NULL,
    total DOUBLE NOT NULL,
    CONSTRAINT state_purchase_fk FOREIGN KEY (state_id) REFERENCES state(state_id),
    CONSTRAINT country_purchase_fk FOREIGN KEY (country_id) REFERENCES country(country_id)
);

CREATE TABLE purchase_item(
    purchase_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    purchase_id BIGINT NOT NULL,
    CONSTRAINT purchase_fk FOREIGN KEY (purchase_id) REFERENCES purchase(purchase_id)
);
