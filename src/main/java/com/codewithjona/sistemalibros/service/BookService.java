package com.codewithjona.sistemalibros.service;

import com.codewithjona.sistemalibros.dtos.BookDTO;
import com.codewithjona.sistemalibros.dtos.CreateBookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDTO createBook(CreateBookDTO dto);
    BookDTO getBookById(Long id);
    List<BookDTO> listBooks();
    BookDTO updateBook(Long id,CreateBookDTO dto);
    void deleteBook(Long id);
    List<BookDTO> getBookByAuthor(Long authorId);
    BookDTO getBookByIsbn(String isbn);
    List<BookDTO> searchBooksByTitle(String title);
    Page<BookDTO> listBooks(Pageable pageable);
    List<BookDTO> searchBooks(String title, Integer year);
}
