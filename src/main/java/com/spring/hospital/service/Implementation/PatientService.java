package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.entity.MedicalHistory;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.entity.User;
import com.spring.hospital.repository.PatientRepository;
import com.spring.hospital.repository.UserRepository;
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
    private final UserRepository userRepository;

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
        Patient existingPatient = patientRepository.findById(patientDTO.getId()).orElse(null);

        if (existingPatient != null) {
            // Map fields from the DTO to the existing patient entity
            existingPatient.setFullName(patientDTO.getFullName());
            existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
            existingPatient.setEmail(patientDTO.getEmail());
            existingPatient.setAddress(patientDTO.getAddress());
            existingPatient.setGender(patientDTO.getGender());
            existingPatient.setPhone(patientDTO.getPhone());

            // Get the existing user and set it to the patient
            Long existingUserId = existingPatient.getUser().getId(); // Get the existing user ID
            User existingUser = userRepository.findById(existingUserId).orElse(null);
            existingPatient.setUser(existingUser);

            // Update the medical history
            if (patientDTO.getMedicalHistory() != null) {
                MedicalHistory existingMedicalHistory = existingPatient.getMedicalHistory();
                if (existingMedicalHistory != null) {
                    // Update the existing medical history with the values from the DTO
                    existingMedicalHistory.setHeight(patientDTO.getMedicalHistory().getHeight());
                    existingMedicalHistory.setWeight(patientDTO.getMedicalHistory().getWeight());
                    existingMedicalHistory.setBloodPressure(patientDTO.getMedicalHistory().getBloodPressure());
                    existingMedicalHistory.setAllergies(patientDTO.getMedicalHistory().getAllergies());
                } else {
                    // Create a new medical history and associate it with the existing patient
                    MedicalHistory medicalHistory = modelMapper.map(patientDTO.getMedicalHistory(), MedicalHistory.class);
                    existingPatient.setMedicalHistory(medicalHistory);
                }
            } else {
                // Set the medical history to null if it's not provided in the DTO
                existingPatient.setMedicalHistory(null);
            }

            // Save the updated patient
            existingPatient = patientRepository.save(existingPatient);

            // Map the updated patient entity back to the DTO
            return modelMapper.map(existingPatient, PatientDTO.class);
        } else {
            // Handle the case where the patient does not exist
            // You can throw an exception or return a specific response
            return null;
        }
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
