package com.spring.hospital.controller;

import com.spring.hospital.dto.DoctorDTO;
import com.spring.hospital.entity.Doctor;
import com.spring.hospital.repository.DoctorRepository;
import com.spring.hospital.service.IDoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;
    @Autowired
    private final ModelMapper modelMapper;

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long doctorId) {
        DoctorDTO doctorDTO = doctorService.getOneDoctor(doctorId);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }



    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
         DoctorDTO  savedDoctorDTO = doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>(savedDoctorDTO, HttpStatus.CREATED);
    }



    @Secured({"ROLE_ADMIN"})
    @PostMapping("/addList")
    public ResponseEntity<List<DoctorDTO>> saveDoctors(@RequestBody List<DoctorDTO> doctorDTOs) {
        List<DoctorDTO> savedDoctorDTOs =  doctorService.saveDoctors(doctorDTOs);
        return new ResponseEntity<>(savedDoctorDTOs, HttpStatus.CREATED);
    }



    @Secured({"ROLE_DOCTOR"})
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctorById(@PathVariable Long doctorId, @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO doctor = doctorService.getOneDoctor(doctorId);
        DoctorDTO updatedDoctorDTO  = doctorService.editDoctor(doctor);
        return new ResponseEntity<>(updatedDoctorDTO, HttpStatus.OK);
    }


    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*
    @PostAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/specialty/{specialtyId}")
    public List<DoctorDTO> getDoctorsBySpecialtyId(@PathVariable Long specialtyId) {
        return doctorService.getDoctorsBySpecialtyId(specialtyId);
    }

     */

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/specialty/{specialityName}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsBySpecialityName(@PathVariable("specialityName") String specialityName) {
        List<DoctorDTO> doctors = doctorService.getDoctorsBySpecialityName(specialityName);
        return ResponseEntity.ok(doctors);
    }




}
