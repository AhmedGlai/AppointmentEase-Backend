package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.repository.PatientRepository;
import com.spring.hospital.service.IPatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PatientService implements IPatientService {
    @Autowired
    private final PatientRepository patientRepository ;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO editPatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<PatientDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> savePatients(List<PatientDTO> patientsDTO) {
        List<Patient> patients = patientsDTO.stream()
                .map(patientDTO -> modelMapper.map(patientDTO, Patient.class))
                .collect(Collectors.toList());
        patients = patientRepository.saveAll(patients);

        return patients.stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getOnePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(patient, PatientDTO.class);
    }
}
