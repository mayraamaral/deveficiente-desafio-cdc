CREATE TABLE book (
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) UNIQUE NOT NULL,
    abstract VARCHAR(500) NOT NULL,
    summary TEXT,
    price DECIMAL(10, 2) NOT NULL,
    pages_number INT NOT NULL,
    isbn TEXT NOT NULL,
    publish_date DATE,
    category_id BIGINT NOT NULL,
    CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category(category_id)
);