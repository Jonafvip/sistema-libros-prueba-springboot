package com.codewithjona.sistemalibros.dtos;

import com.codewithjona.sistemalibros.enums.Genres;
import com.codewithjona.sistemalibros.enums.LiteraryGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Genres genres;
    private String pseudonym;
    private LiteraryGenre literaryGenre;
}
