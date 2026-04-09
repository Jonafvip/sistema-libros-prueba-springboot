package com.codewithjona.sistemalibros.dtos;

import com.codewithjona.sistemalibros.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private CategoryType categoryType;
    private Double assessment;
}
