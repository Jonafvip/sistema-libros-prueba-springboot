package com.codewithjona.sistemalibros.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DvDDTO {
    private Long id;
    private String name;
    private String duration;
    private LocalDate releaseDate;
    private Long directorId;
    private String directorName;
    private Set<Long> categoryIds;
    private Set<String> categoryTypes;
}
