package com.codewithjona.sistemalibros.service;

import com.codewithjona.sistemalibros.dtos.AuthorDTO;
import com.codewithjona.sistemalibros.dtos.CreateAuthorDTO;
import com.codewithjona.sistemalibros.entities.Author;
import com.codewithjona.sistemalibros.enums.Genres;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import com.codewithjona.sistemalibros.repository.AuthorRepository;
import com.codewithjona.sistemalibros.service.Impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private CreateAuthorDTO createAuthorDTO;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setId(1L);
        author.setName("J.K.");
        author.setLastName("Rowling");
        author.setBirthDate(LocalDate.of(1965, 7, 31));
        author.setGenres(Genres.FEMALE);
        author.setPseudonym("J.K. Rowling");

        createAuthorDTO = new CreateAuthorDTO();
        createAuthorDTO.setName("J.K.");
        createAuthorDTO.setLastName("Rowling");
        createAuthorDTO.setBirthDate(LocalDate.of(1965, 7, 31));
        createAuthorDTO.setGenres(Genres.FEMALE);
        createAuthorDTO.setPseudonym("J.K. Rowling");
    }

    @Test
    void createAuthor_ShouldReturnAuthorDTO() {
        // Given
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        // When
        AuthorDTO result = authorService.createAuthor(createAuthorDTO);
        // Then
        assertNotNull(result);
        assertEquals("J.K.", result.getName());
        assertEquals("Rowling", result.getLastName());
    }

    @Test
    void getAuthorById_ShouldReturnAuthor_WhenExists() {
        // Given
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        // When
        AuthorDTO result = authorService.getAuthorById(1L);
        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("J.K.", result.getName());
    }

    @Test
    void getAuthorById_ShouldThrowException_WhenNotExists() {
        // Given
        when(authorRepository.findById(999L)).thenReturn(Optional.empty());
        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.getAuthorById(999L);
        });
    }

    @Test
    void listAuthors_ShouldReturnListOfAuthors() {
        // Given
        when(authorRepository.findAll()).thenReturn(List.of(author));
        // When
        List<AuthorDTO> result = authorService.listAuthors();
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("J.K.", result.get(0).getName());
    }
}
