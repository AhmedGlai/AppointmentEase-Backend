package com.spring.hospital.repository;

import com.spring.hospital.entity.Consultation;
import com.spring.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findPatientByName(String name);
    Optional <Patient> findPatientById(Long Id);
}
