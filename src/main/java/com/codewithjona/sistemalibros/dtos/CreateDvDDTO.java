package com.codewithjona.sistemalibros.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDvDDTO {

    @NotBlank
    @Size(min = 3 , max = 50, message = "The name must be at least 3 characters long")
    private String name;

    @NotBlank
    private String duration;

    @NotNull(message = "The releaseDate is required")
    private LocalDate releaseDate;

    @NotNull
    private Long directorId;

    private Set<Long> categoryIds;
}
