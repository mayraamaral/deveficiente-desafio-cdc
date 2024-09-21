package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.book.Book;
import dev.mayra.seeddesafiocdc.model.book.BookMinifiedProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = """
        SELECT
            book_id AS id,
            title
        FROM book
    """, nativeQuery = true)
    List<BookMinifiedProjection> findAllMinified();
}
