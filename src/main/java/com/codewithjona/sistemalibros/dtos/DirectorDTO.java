package com.codewithjona.sistemalibros.dtos;

import com.codewithjona.sistemalibros.enums.Genres;
import com.codewithjona.sistemalibros.enums.LiteraryGenre;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDTO {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Genres genres;
    private String studies;
    private String awards;
    private Integer numMovies;
}
