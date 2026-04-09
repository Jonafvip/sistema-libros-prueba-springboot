package com.codewithjona.sistemalibros.service.Impl;

import com.codewithjona.sistemalibros.dtos.CreateDirectorDTO;
import com.codewithjona.sistemalibros.dtos.DirectorDTO;
import com.codewithjona.sistemalibros.entities.Director;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import com.codewithjona.sistemalibros.repository.DirectorRepository;
import com.codewithjona.sistemalibros.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    public DirectorDTO createDirector(CreateDirectorDTO dto) {
        Director director = new Director();
        director.setName(dto.getName());
        director.setLastName(dto.getLastName());
        director.setBirthDate(dto.getBirthDate());
        director.setGenres(dto.getGenres());
        director.setStudies(dto.getStudies());
        director.setAwards(dto.getAwards());
        director.setNumMovies(dto.getNumMovies());

        Director savedDirector = directorRepository.save(director);
        return convertToDirectorDTO(savedDirector);
    }

    @Override
    public List<DirectorDTO> listDirectors() {
        List<Director> directors = directorRepository.findAll();
        return directors.stream()
                .map(this::convertToDirectorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not Found by id: " + id));

        return convertToDirectorDTO(director);
    }

    @Override
    public DirectorDTO updateDirector(Long id, CreateDirectorDTO dto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found"));

        director.setName(dto.getName());
        director.setLastName(dto.getLastName());
        director.setBirthDate(dto.getBirthDate());
        director.setGenres(dto.getGenres());
        director.setStudies(dto.getStudies());
        director.setAwards(dto.getAwards());
        director.setNumMovies(dto.getNumMovies());

        Director savedDirector = directorRepository.save(director);
        return convertToDirectorDTO(savedDirector);
    }

    @Override
    public void deleteDirector(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found"));

        directorRepository.deleteById(director.getId());
    }

    private DirectorDTO convertToDirectorDTO(Director director) {

        return DirectorDTO.builder()
                .id(director.getId())
                .name(director.getName())
                .lastName(director.getLastName())
                .birthDate(director.getBirthDate())
                .genres(director.getGenres())
                .studies(director.getStudies())
                .numMovies(director.getNumMovies())
                .awards(director.getAwards())
                .build();
    }
}
