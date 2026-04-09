package com.codewithjona.sistemalibros.service;

import com.codewithjona.sistemalibros.dtos.CreateDirectorDTO;
import com.codewithjona.sistemalibros.dtos.DirectorDTO;

import java.util.List;

public interface DirectorService {
    DirectorDTO createDirector(CreateDirectorDTO dto);
    List<DirectorDTO> listDirectors();
    DirectorDTO getDirectorById(Long id);
    DirectorDTO updateDirector(Long id, CreateDirectorDTO dto);
    void deleteDirector(Long id);
}
