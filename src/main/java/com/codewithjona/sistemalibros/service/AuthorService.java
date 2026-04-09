package com.codewithjona.sistemalibros.service;

import com.codewithjona.sistemalibros.dtos.AuthorDTO;
import com.codewithjona.sistemalibros.dtos.CreateAuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(CreateAuthorDTO dto);
    AuthorDTO getAuthorById(Long id);
    List<AuthorDTO> listAuthors();
    AuthorDTO updateAuthor(Long id, CreateAuthorDTO dto);
    void deleteAuthor(Long id);
}
