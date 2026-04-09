package com.codewithjona.sistemalibros.controller;

import com.codewithjona.sistemalibros.dtos.CategoryDTO;
import com.codewithjona.sistemalibros.dtos.CreateCategoryDTO;
import com.codewithjona.sistemalibros.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(
            @Valid @RequestBody CreateCategoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(
            @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")

    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CreateCategoryDTO dto) {
        return ResponseEntity.ok(
                categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(204).build();
    }
}
