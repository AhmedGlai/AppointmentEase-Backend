package com.spring.hospital.controller;

import com.spring.hospital.dto.MedicalHistoryDTO;
import com.spring.hospital.entity.MedicalHistory;
import com.spring.hospital.service.IMedicalHistoryService;
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
@RequestMapping("/api/medical-histories")
@AllArgsConstructor
public class MedicalHistoryController {

    private final IMedicalHistoryService medicalHistoryService;
    private final ModelMapper modelMapper;

    @Secured({"ROLE_DOCTOR"})
    @PostMapping("/add")
    public ResponseEntity<MedicalHistoryDTO> createMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        MedicalHistoryDTO savedMedicalHistoryDTO = medicalHistoryService.saveMedicalHistory(medicalHistoryDTO);
        return new ResponseEntity<>(savedMedicalHistoryDTO, HttpStatus.CREATED);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistoryDTO> getOneMedicalHistory(@PathVariable Long id) {
        MedicalHistoryDTO medicalHistoryDTO = medicalHistoryService.getOneMedicalHistory(id);
        return new ResponseEntity<>(medicalHistoryDTO, HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("")
    public ResponseEntity<List<MedicalHistoryDTO>> getMedicalHistories() {
        List<MedicalHistoryDTO> medicalHistoryDTOs = medicalHistoryService.getMedicalHistories();
        return new ResponseEntity<>(medicalHistoryDTOs, HttpStatus.OK);
    }
}
