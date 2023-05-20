package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.DoctorDTO;
import com.spring.hospital.entity.Doctor;
import com.spring.hospital.repository.DoctorRepository;
import com.spring.hospital.service.IDoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService implements IDoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctor = doctorRepository.save(doctor);
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public DoctorDTO editDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
         doctor =  doctorRepository.save(doctor);
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> saveDoctors(List<DoctorDTO> doctorDTOs) {
        List<Doctor> doctors =  doctorDTOs.stream()
                .map(doctorDTO -> modelMapper.map(doctorDTO, Doctor.class))
                .collect(Collectors.toList());
        doctors = doctorRepository.saveAll(doctors);

        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getOneDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    public List<DoctorDTO> getDoctorsBySpecialityName(String specialityName) {
        List<Doctor> doctors = doctorRepository.findBySpecialtyName(specialityName);
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }


/*
    @Override
    public List<DoctorDTO> getDoctorsBySpecialityName(String specialityName) {
        List<Doctor> doctors = doctorRepository.findBySpecialty_Name(specialityName);
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
                .collect(Collectors.toList());
    }

 */
}



