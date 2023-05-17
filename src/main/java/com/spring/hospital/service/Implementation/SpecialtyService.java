package com.spring.hospital.service.Implementation;


import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.dto.SpecialtyDTO;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.entity.Specialty;
import com.spring.hospital.repository.SpecialtyRepository;
import com.spring.hospital.service.ISpecialtyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecialtyService implements ISpecialtyService {

    @Autowired
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public SpecialtyDTO saveSpecialty(SpecialtyDTO specialtyDTO) {
        Specialty specialty = modelMapper.map(specialtyDTO, Specialty.class);
        specialty = specialtyRepository.save(specialty);
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }

    @Override
    public SpecialtyDTO editSpecialty(SpecialtyDTO specialtyDTO) {
        Specialty specialty = modelMapper.map(specialtyDTO, Specialty.class);
        specialty = specialtyRepository.save(specialty);
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }

    @Override
    public void deleteSpecialty(Long specialtyId) {
        specialtyRepository.deleteById(specialtyId);
    }



    @Override
    public List<SpecialtyDTO> getSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialties.stream()
                .map(specialty -> modelMapper.map(specialty, SpecialtyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialtyDTO> saveSpecialties(List<SpecialtyDTO> specialtiesDTO) {
        List<Specialty> specialties = specialtiesDTO.stream()
                .map(specialtyDTO -> modelMapper.map(specialtyDTO, Specialty.class))
                .collect(Collectors.toList());
        specialties = specialtyRepository.saveAll(specialties);

        return specialties.stream()
                .map(specialty -> modelMapper.map(specialty, SpecialtyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO getOneSpecialty(Long specialtyId) {
        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(specialty, SpecialtyDTO.class);
    }
}
