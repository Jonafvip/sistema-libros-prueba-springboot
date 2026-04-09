package com.codewithjona.sistemalibros.service.Impl;

import com.codewithjona.sistemalibros.dtos.AuthorDTO;
import com.codewithjona.sistemalibros.dtos.CreateAuthorDTO;
import com.codewithjona.sistemalibros.entities.Author;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import com.codewithjona.sistemalibros.repository.AuthorRepository;
import com.codewithjona.sistemalibros.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDTO createAuthor(CreateAuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setLastName(dto.getLastName());
        author.setBirthDate(dto.getBirthDate());
        author.setGenres(dto.getGenres());
        author.setPseudonym(dto.getPseudonym());
        author.setPublisher(dto.getPublisher());
        author.setLiteraryGenre(dto.getLiteraryGenre());

        Author savedAuthor = authorRepository.save(author);
        return convertToAuthorDTO(savedAuthor);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author findAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author Not Found by id" + id));

        return convertToAuthorDTO(findAuthor);
    }

    @Override
    public List<AuthorDTO> listAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(this::convertToAuthorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO updateAuthor(Long id, CreateAuthorDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found by id" + id));

        author.setName(dto.getName());
        author.setLastName(dto.getLastName());
        author.setBirthDate(dto.getBirthDate());
        author.setPseudonym(dto.getPseudonym());
        author.setPublisher(dto.getPublisher());
        author.setGenres(dto.getGenres());
        author.setLiteraryGenre(dto.getLiteraryGenre());

        Author updateAuthor = authorRepository.save(author);
        return convertToAuthorDTO(updateAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found by id" + id));

        authorRepository.deleteById(author.getId());
    }


    private AuthorDTO convertToAuthorDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .lastName(author.getLastName())
                .birthDate(author.getBirthDate())
                .pseudonym(author.getPseudonym())
                .genres(author.getGenres())
                .literaryGenre(author.getLiteraryGenre())
                .build();
    }
}
