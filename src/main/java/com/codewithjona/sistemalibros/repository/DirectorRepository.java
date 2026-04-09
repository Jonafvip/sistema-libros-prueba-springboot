package com.codewithjona.sistemalibros.repository;

import com.codewithjona.sistemalibros.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Long> {
}
