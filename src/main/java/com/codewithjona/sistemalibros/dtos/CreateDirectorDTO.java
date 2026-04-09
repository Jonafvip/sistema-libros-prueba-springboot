package com.codewithjona.sistemalibros.dtos;

import com.codewithjona.sistemalibros.enums.Genres;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDirectorDTO {
    @NotBlank
    @Size(min = 3 , max = 50, message = "The name must be at least 3 characters long")
    private String name;

    @NotBlank
    @Size(min = 3 , max = 50, message = "The lastname must be at least 3 characters long")
    private String lastName;

    @NotNull(message = "The birthDate is required")
    private LocalDate birthDate;

    @NotNull(message = "The genres is required")
    private Genres genres;

    @NotBlank(message = "The studies is required")
    private String studies;

    private String awards;

    private Integer numMovies;
}
