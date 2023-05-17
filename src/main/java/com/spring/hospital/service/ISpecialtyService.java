package com.spring.hospital.service;

import com.spring.hospital.dto.DoctorDTO;
import com.spring.hospital.dto.SpecialtyDTO;
import com.spring.hospital.entity.Doctor;

import java.util.Collection;
import java.util.List;

public interface ISpecialtyService {
    SpecialtyDTO saveSpecialty(SpecialtyDTO specialty);
    SpecialtyDTO editSpecialty(SpecialtyDTO specialty);
    void deleteSpecialty(Long specialtyId);

    List<SpecialtyDTO> getSpecialties();
    List<SpecialtyDTO> saveSpecialties(List<SpecialtyDTO> specialties);
    SpecialtyDTO getOneSpecialty(Long specialtyId);
    Collection<DoctorDTO> getDoctorsBySpecialityName(String specialityName);

}
