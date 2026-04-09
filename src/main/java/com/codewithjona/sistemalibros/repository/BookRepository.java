package com.codewithjona.sistemalibros.repository;

import com.codewithjona.sistemalibros.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorId(Long authorId);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByTitleContaining(String title);
    Page<Book> findAll(Pageable pageable);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByYearOfPublication(Integer year);
    List<Book> findByTitleContainingIgnoreCaseAndYearOfPublication(String title,Integer year);
}
