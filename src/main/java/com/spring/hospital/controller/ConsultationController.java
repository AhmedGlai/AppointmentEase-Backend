package com.spring.hospital.controller;

import com.spring.hospital.entity.Consultation;
import com.spring.hospital.service.IAppointmentService;
import com.spring.hospital.service.IConsultationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@Secured({"ADMIN", "PATIENT","DOCTOR"})
@AllArgsConstructor
public class ConsultationController {

    @Autowired
    private final IConsultationService consultationService;
    @Autowired
    private final IAppointmentService appointmentService;



    @Secured({"ROLE_ADMIN","ROLE_DOCTOR"})
    @PostMapping("/add")
    public ResponseEntity<Consultation> saveConsultation(@RequestBody Consultation consultation) {
        return new ResponseEntity<>(consultationService.saveConsultation(consultation), HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_DOCTOR"})
    @PutMapping("/update")
    public ResponseEntity<Consultation> editConsultation(@RequestBody Consultation consultation) {
        return new ResponseEntity<>(consultationService.editConsultation(consultation), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{consultationId}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long consultationId) {
        consultationService.deleteConsultation(consultationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("")
    public ResponseEntity<List<Consultation>> getConsultations() {
        return new ResponseEntity<>(consultationService.getConsultations(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/{consultationId}")
    public ResponseEntity<Consultation> getOneConsultation(@PathVariable Long consultationId) {
        Consultation consultation = consultationService.getOneConsultation(consultationId);
        if (consultation == null) {
            throw new EntityNotFoundException("Consultation with ID " + consultationId + " not found.");
        }
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }
}
