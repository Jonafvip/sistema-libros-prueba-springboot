package com.codewithjona.sistemalibros.controller;

import com.codewithjona.sistemalibros.dtos.AuthorDTO;
import com.codewithjona.sistemalibros.dtos.CreateAuthorDTO;
import com.codewithjona.sistemalibros.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody CreateAuthorDTO dto) {
        AuthorDTO authorDTO = authorService.createAuthor(dto);
        return new ResponseEntity<>(authorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDTO>> listAuthors() {
        return ResponseEntity.ok(authorService.listAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @Valid @RequestBody CreateAuthorDTO dto) {
        AuthorDTO authorDTO = authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(204).build();
    }
}
