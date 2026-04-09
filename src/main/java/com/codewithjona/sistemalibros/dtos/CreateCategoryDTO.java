package com.codewithjona.sistemalibros.dtos;

import com.codewithjona.sistemalibros.enums.CategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDTO {

    @NotNull(message = "Category type is required")
    private CategoryType categoryType;

    private Double assessment;
}
