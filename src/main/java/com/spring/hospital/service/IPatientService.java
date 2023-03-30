package com.spring.hospital.service;

import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    PatientDTO savePatient (PatientDTO patient);
    PatientDTO editPatient(PatientDTO patient);
    void deletePatient (Long patientId);

    List<PatientDTO> getPatients();
    public List<PatientDTO> savePatients(List<PatientDTO> patients);

     PatientDTO getOnePatient(Long patientId);




}
