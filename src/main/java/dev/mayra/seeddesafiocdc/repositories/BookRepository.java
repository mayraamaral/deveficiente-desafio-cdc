package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
