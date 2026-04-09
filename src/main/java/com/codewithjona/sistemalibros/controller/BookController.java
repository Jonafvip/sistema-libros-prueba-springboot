package com.codewithjona.sistemalibros.controller;

import com.codewithjona.sistemalibros.dtos.BookDTO;
import com.codewithjona.sistemalibros.dtos.CreateBookDTO;
import com.codewithjona.sistemalibros.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookDTO dto) {
        BookDTO bookDTO = bookService.createBook(dto);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> listBooks() {
        List<BookDTO> bookDTO = bookService.listBooks();
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getByIdBook(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookDTO>> getBookByAuthor(@PathVariable Long authorId) {
        List<BookDTO> bookDTOS = bookService.getBookByAuthor(authorId);
        return ResponseEntity.ok(bookDTOS);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) {
        BookDTO bookDTO = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooksByTitle(@RequestParam(required = false) String title) {
        List<BookDTO> bookDTOS = bookService.searchBooksByTitle(title);
        return ResponseEntity.ok(bookDTOS);
    }

    @GetMapping("/search2")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(bookService.searchBooks(title, year));
    }

    //pagination
    @GetMapping("/paginated")
    public ResponseEntity<Page<BookDTO>> listBooksPaginated(Pageable pageable) {
        return ResponseEntity.ok(bookService.listBooks(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody CreateBookDTO dto) {
        BookDTO bookDTO = bookService.updateBook(id, dto);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(204).build();
    }
}
