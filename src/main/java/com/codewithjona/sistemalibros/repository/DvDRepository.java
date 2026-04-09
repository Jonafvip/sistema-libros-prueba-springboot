package com.codewithjona.sistemalibros.repository;

import com.codewithjona.sistemalibros.entities.DvD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DvDRepository extends JpaRepository<DvD,Long> {
    List<DvD> findByDirectorId(Long directorId);
}
