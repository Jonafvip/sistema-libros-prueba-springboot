package com.codewithjona.sistemalibros.service;

import com.codewithjona.sistemalibros.dtos.CategoryDTO;
import com.codewithjona.sistemalibros.dtos.CreateCategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CreateCategoryDTO dto);
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> listCategories();
    CategoryDTO updateCategory(Long id, CreateCategoryDTO dto);
    void deleteCategory(Long id);
}
