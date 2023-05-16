package com.spring.hospital.controller;

import com.spring.hospital.dto.SpecialtyDTO;
import com.spring.hospital.service.ISpecialtyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/specialties")
@AllArgsConstructor
@Secured({"ADMIN", "PATIENT","DOCTOR"})
public class SpecialtyController {

    private final ISpecialtyService specialtyService;
    private final ModelMapper modelMapper;

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<SpecialtyDTO> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        SpecialtyDTO savedSpecialtyDTO = specialtyService.saveSpecialty(specialtyDTO);
        return new ResponseEntity<>(savedSpecialtyDTO, HttpStatus.CREATED);
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addList")
    public ResponseEntity<List<SpecialtyDTO>> saveSpecialties(@RequestBody List<SpecialtyDTO> specialtyDTOs) {
        List<SpecialtyDTO> savedSpecialtyDTOs = specialtyService.saveSpecialties(specialtyDTOs);
        return new ResponseEntity<>(savedSpecialtyDTOs, HttpStatus.CREATED);
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getOneSpecialty(@PathVariable Long id) {
        SpecialtyDTO specialtyDTO = specialtyService.getOneSpecialty(id);
        return new ResponseEntity<>(specialtyDTO, HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    @GetMapping("")
    public ResponseEntity<List<SpecialtyDTO>> getSpecialties() {
        List<SpecialtyDTO> specialtyDTOs = specialtyService.getSpecialties();
        return new ResponseEntity<>(specialtyDTOs, HttpStatus.OK);
    }
}

