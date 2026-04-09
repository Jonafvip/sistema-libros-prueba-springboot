package com.codewithjona.sistemalibros.controller;

import com.codewithjona.sistemalibros.dtos.CreateDirectorDTO;
import com.codewithjona.sistemalibros.dtos.DirectorDTO;
import com.codewithjona.sistemalibros.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping("/")
    public ResponseEntity<DirectorDTO> createDirector (@Valid @RequestBody CreateDirectorDTO dto){
        DirectorDTO directorDTO = directorService.createDirector(dto);
        return new ResponseEntity<>(directorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DirectorDTO>> listDirectors(){
        List<DirectorDTO> directorDTOS = directorService.listDirectors();
        return ResponseEntity.ok(directorDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> getDirectorById(@PathVariable Long id){
        DirectorDTO directorDTO = directorService.getDirectorById(id);
        return ResponseEntity.ok(directorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> updateDirector(@PathVariable Long id, @Valid @RequestBody CreateDirectorDTO dto){
        DirectorDTO directorDTO = directorService.updateDirector(id,dto);
        return ResponseEntity.ok(directorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable Long id){
        directorService.deleteDirector(id);
        return ResponseEntity.status(204).build();
    }
}
