package com.codewithjona.sistemalibros.service.Impl;

import com.codewithjona.sistemalibros.dtos.BookDTO;
import com.codewithjona.sistemalibros.dtos.CreateBookDTO;
import com.codewithjona.sistemalibros.entities.Author;
import com.codewithjona.sistemalibros.entities.Book;
import com.codewithjona.sistemalibros.entities.Category;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import com.codewithjona.sistemalibros.repository.AuthorRepository;
import com.codewithjona.sistemalibros.repository.BookRepository;
import com.codewithjona.sistemalibros.repository.CategoryRepository;
import com.codewithjona.sistemalibros.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDTO createBook(CreateBookDTO dto) {

        Author authorExist = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("The author not exist"));

        Set<Category> categories = new HashSet<>();
        if(dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()){
            categories = new HashSet<>(categoryRepository.findByIdIn(dto.getCategoryIds()));
        }


        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setYearOfPublication(dto.getYearOfPublication());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setIsbn(dto.getIsbn());
        book.setAuthor(authorExist);
        book.setCategories(categories);

        Book savedBook = bookRepository.save(book);
        return convertToBookDTO(savedBook);
    }

    @Override
    public BookDTO getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return convertToBookDTO(book);
    }

    @Override
    public List<BookDTO> listBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Long id, CreateBookDTO dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(dto.getAuthorId())
                        .orElseThrow(() -> new ResourceNotFoundException("The author not exist"));

        Set<Category> categories = new HashSet<>();
        if(dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()){
            categories = new HashSet<>(categoryRepository.findByIdIn(dto.getCategoryIds()));
        }

        book.setTitle(dto.getTitle());
        book.setYearOfPublication(dto.getYearOfPublication());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setIsbn(dto.getIsbn());
        book.setAuthor(author);
        book.setCategories(categories);

        Book update = bookRepository.save(book);
        return convertToBookDTO(update);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Book not found by id: " + id));

        bookRepository.deleteById(book.getId());
    }

    @Override
    public List<BookDTO> getBookByAuthor(Long authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);

        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Book  book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found by: " + isbn));

        return convertToBookDTO(book);
    }

    @Override
    public List<BookDTO> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> searchBooks(String title,Integer year){
        List<Book> books;

        if(title != null && year != null){
            books = bookRepository.findByTitleContainingIgnoreCaseAndYearOfPublication(title,year);
        }else if(title != null){
            books = bookRepository.findByTitleContainingIgnoreCase(title);
        }else if(year != null){
            books = bookRepository.findByYearOfPublication(year);
        }else{
            books = bookRepository.findAll();
        }
        return books.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    //Paginacion
    @Override
    public Page<BookDTO> listBooks(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.map(this::convertToBookDTO);
    }



    private BookDTO convertToBookDTO(Book book) {

        Set<Long> categoryIds = new HashSet<>();
        Set<String> categoryTypes = new HashSet<>();

        if(book.getCategories() != null){
            book.getCategories().forEach(category ->
            {
                categoryIds.add(category.getId());
                categoryTypes.add(category.getCategoryType().name());
            });
        }

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .yearOfPublication(book.getYearOfPublication())
                .numberOfPages(book.getNumberOfPages())
                .isbn(book.getIsbn())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getName())
                .categoryIds(categoryIds)
                .categoryTypes(categoryTypes)
                .build();
    }
}
