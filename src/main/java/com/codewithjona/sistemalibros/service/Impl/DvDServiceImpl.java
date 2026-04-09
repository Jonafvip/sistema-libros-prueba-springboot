package com.codewithjona.sistemalibros.service.Impl;

import com.codewithjona.sistemalibros.dtos.CreateDvDDTO;
import com.codewithjona.sistemalibros.dtos.DvDDTO;
import com.codewithjona.sistemalibros.entities.Category;
import com.codewithjona.sistemalibros.entities.Director;
import com.codewithjona.sistemalibros.entities.DvD;
import com.codewithjona.sistemalibros.exceptions.ResourceNotFoundException;
import com.codewithjona.sistemalibros.repository.CategoryRepository;
import com.codewithjona.sistemalibros.repository.DirectorRepository;
import com.codewithjona.sistemalibros.repository.DvDRepository;
import com.codewithjona.sistemalibros.service.DvDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DvDServiceImpl implements DvDService {

    private final DvDRepository dvDRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public DvDDTO createDvD(CreateDvDDTO dto) {

        Director directorExist = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found by id: " + dto.getDirectorId()));

        Set<Category> categories = new HashSet<>();
        if(dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()){
            categories = new HashSet<>(categoryRepository.findByIdIn(dto.getCategoryIds()));
        }

        DvD dvd = new DvD();
        dvd.setName(dto.getName());
        dvd.setReleaseDate(dto.getReleaseDate());
        dvd.setDuration(dto.getDuration());
        dvd.setDirector(directorExist);
        dvd.setCategories(categories);

        DvD savedDvD = dvDRepository.save(dvd);
        return convertDvDDTO(savedDvD);
    }

    @Override
    public DvDDTO getDvDById(Long id) {
        DvD dvd = dvDRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dvd not found by id: " + id));

        return convertDvDDTO(dvd);
    }

    @Override
    public List<DvDDTO> listDvDs() {
        List<DvD> dvDList = dvDRepository.findAll();

        return dvDList.stream()
                .map(this::convertDvDDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DvDDTO updateDvD(Long id, CreateDvDDTO dto) {

        DvD dvd = dvDRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DvD not found"));

        Director director = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Director not found"));

        Set<Category> categories = new HashSet<>();
        if(dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()){
            categories = new HashSet<>(categoryRepository.findByIdIn(dto.getCategoryIds()));
        }
        dvd.setName(dto.getName());
        dvd.setDuration(dto.getDuration());
        dvd.setReleaseDate(dto.getReleaseDate());
        dvd.setDirector(director);
        dvd.setCategories(categories);

        DvD savedDvD = dvDRepository.save(dvd);
        return convertDvDDTO(savedDvD);
    }

    @Override
    public void deleteDvD(Long id) {

        DvD dvd = dvDRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DvD not found"));

        dvDRepository.deleteById(dvd.getId());
    }

    @Override
    public List<DvDDTO> getDvDByDirector(Long directorId) {
        List<DvD> dvds = dvDRepository.findByDirectorId(directorId);

        return dvds.stream()
                .map(this::convertDvDDTO)
                .collect(Collectors.toList());
    }

    private DvDDTO convertDvDDTO(DvD dvd) {

        Set<Long> categoryIds = new HashSet<>();
        Set<String> categoryTypes = new HashSet<>();

        if(dvd.getCategories() != null){
            dvd.getCategories().forEach(category ->
            {
                categoryIds.add(category.getId());
                categoryTypes.add(category.getCategoryType().name());
            });
        }
        return DvDDTO.builder()
                .id(dvd.getId())
                .name(dvd.getName())
                .releaseDate(dvd.getReleaseDate())
                .duration(dvd.getDuration())
                .directorId(dvd.getDirector().getId())
                .directorName(dvd.getDirector().getName())
                .categoryIds(categoryIds)
                .categoryTypes(categoryTypes)
                .build();
    }
}
