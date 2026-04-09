package com.codewithjona.sistemalibros.service;

import com.codewithjona.sistemalibros.dtos.CreateDvDDTO;
import com.codewithjona.sistemalibros.dtos.DvDDTO;

import java.util.List;

public interface DvDService {
    DvDDTO createDvD(CreateDvDDTO dto);
    DvDDTO getDvDById(Long id);
    List<DvDDTO> listDvDs();
    DvDDTO updateDvD(Long id,CreateDvDDTO dto);
    void deleteDvD(Long id);
    List<DvDDTO> getDvDByDirector(Long directorId);
}
