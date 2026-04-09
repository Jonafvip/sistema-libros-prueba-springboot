package com.codewithjona.sistemalibros.dtos;

import com.codewithjona.sistemalibros.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private Integer yearOfPublication;
    private Integer numberOfPages;
    private String isbn;
    private Long authorId;
    private String authorName;
    private Set<Long> categoryIds;
    private Set<String> categoryTypes;
}
