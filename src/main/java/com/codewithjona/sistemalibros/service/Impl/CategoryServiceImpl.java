package com.codewithjona.sistemalibros.service.Impl;


import com.codewithjona.sistemalibros.dtos.CategoryDTO;
import com.codewithjona.sistemalibros.dtos.CreateCategoryDTO;
import com.codewithjona.sistemalibros.entities.Category;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import com.codewithjona.sistemalibros.repository.CategoryRepository;
import com.codewithjona.sistemalibros.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CreateCategoryDTO dto) {
        Category category = new Category();
        category.setCategoryType(dto.getCategoryType());
        category.setAssessment(dto.getAssessment());
        Category saved = categoryRepository.save(category);
        return convertToDTO(saved);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return convertToDTO(category);
    }

    @Override
    public List<CategoryDTO> listCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CreateCategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setCategoryType(dto.getCategoryType());
        category.setAssessment(dto.getAssessment());
        return convertToDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryRepository.deleteById(category.getId());
    }

    private CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .categoryType(category.getCategoryType())
                .assessment(category.getAssessment())
                .build();
    }
}

