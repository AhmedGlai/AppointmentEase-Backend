package com.spring.hospital.service;

import com.spring.hospital.dto.SpecialtyDTO;

import java.util.List;

public interface ISpecialtyService {
    SpecialtyDTO saveSpecialty(SpecialtyDTO specialty);
    SpecialtyDTO editSpecialty(SpecialtyDTO specialty);
    void deleteSpecialty(Long specialtyId);

    List<SpecialtyDTO> getSpecialties();
    List<SpecialtyDTO> saveSpecialties(List<SpecialtyDTO> specialties);

    SpecialtyDTO getOneSpecialty(Long specialtyId);
}
