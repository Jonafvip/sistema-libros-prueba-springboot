package com.codewithjona.sistemalibros.repository;

import com.codewithjona.sistemalibros.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByIdIn(Set<Long>ids);
}
