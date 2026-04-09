package com.codewithjona.sistemalibros.controller;

import com.codewithjona.sistemalibros.dtos.CreateDvDDTO;
import com.codewithjona.sistemalibros.dtos.DvDDTO;
import com.codewithjona.sistemalibros.service.DvDService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dvds")
@RequiredArgsConstructor
public class DvDController {

    private final DvDService dvDService;

    @PostMapping("/")
    public ResponseEntity<DvDDTO> createDvD(@Valid @RequestBody CreateDvDDTO dto){
        DvDDTO dvDDTO = dvDService.createDvD(dto);
        return new ResponseEntity<>(dvDDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DvDDTO>> listDvds(){
        List<DvDDTO> dvDDTO = dvDService.listDvDs();
        return ResponseEntity.ok(dvDDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DvDDTO> getDvdById(@PathVariable Long id){
        DvDDTO dvDDTO = dvDService.getDvDById(id);
        return ResponseEntity.ok(dvDDTO);
    }

    @GetMapping("/director/{directorId}")
    public ResponseEntity<List<DvDDTO>> getDvDByDirector(@PathVariable Long directorId){
        List<DvDDTO> dvDDTOS = dvDService.getDvDByDirector(directorId);
        return ResponseEntity.ok(dvDDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DvDDTO> updateDvD(@PathVariable Long id, @Valid @RequestBody CreateDvDDTO dto){
        DvDDTO dvDDTO = dvDService.updateDvD(id,dto);
        return ResponseEntity.ok(dvDDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDvD (@PathVariable Long id){
        dvDService.deleteDvD(id);
        return ResponseEntity.status(204).build();
    }
}
