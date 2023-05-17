package com.spring.hospital.controller;

import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
@Secured({"ADMIN"})
@AllArgsConstructor
public class PatientController {

    private final IPatientService patientService;
    private final ModelMapper modelMapper;


    @PostMapping("/add")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO savedPatientDTO = patientService.savePatient(patientDTO);

        return new ResponseEntity<>(savedPatientDTO, HttpStatus.CREATED);
    }



    @PostMapping("/addList")
    public ResponseEntity<List<PatientDTO>> savePatients(@RequestBody List<PatientDTO> patientDTOs) {
        List<PatientDTO> savedPatientDTOs = patientService.savePatients(patientDTOs);
        return new ResponseEntity<>(savedPatientDTOs, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getOnePatient(@PathVariable Long id) {
        PatientDTO patientDTO = patientService.getOnePatient(id);
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> patientDTOs = patientService.getPatients();
        return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
    }
}