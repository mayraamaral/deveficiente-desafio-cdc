package dev.mayra.seeddesafiocdc.repositories;

import dev.mayra.seeddesafiocdc.model.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
