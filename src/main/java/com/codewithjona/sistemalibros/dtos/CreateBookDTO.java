package com.codewithjona.sistemalibros.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDTO {

    @NotBlank
    @Size(min = 3 , max = 50, message = "The title must be at least 3 characters long")
    private String title;

    @NotNull(message = "The yearOfPublication is required")
    private Integer yearOfPublication;

    @NotNull(message = "The numberOfPages is required")
    private Integer numberOfPages;

    @NotBlank(message = "The isbn is required")
    private String isbn;

    @NotNull
    private Long authorId;

    private Set<Long> categoryIds;
}
