package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.MedicalHistoryDTO;
import com.spring.hospital.entity.MedicalHistory;
import com.spring.hospital.repository.MedicalHistoryRepository;
import com.spring.hospital.service.IMedicalHistoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicalHistoryService implements IMedicalHistoryService {
    @Autowired
    private final MedicalHistoryRepository medicalHistoryRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public MedicalHistoryDTO saveMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) {
        MedicalHistory medicalHistory = modelMapper.map(medicalHistoryDTO, MedicalHistory.class);
        medicalHistory = medicalHistoryRepository.save(medicalHistory);
        return modelMapper.map(medicalHistory, MedicalHistoryDTO.class);
    }

    @Override
    public MedicalHistoryDTO editMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) {
        MedicalHistory medicalHistory = modelMapper.map(medicalHistoryDTO, MedicalHistory.class);
        medicalHistory = medicalHistoryRepository.save(medicalHistory);
        return modelMapper.map(medicalHistory, MedicalHistoryDTO.class);
    }

    @Override
    public void deleteMedicalHistory(Long medicalHistoryId) {
        medicalHistoryRepository.deleteById(medicalHistoryId);
    }

    @Override
    public List<MedicalHistoryDTO> getMedicalHistories() {
        List<MedicalHistory> medicalHistories = medicalHistoryRepository.findAll();
        return medicalHistories.stream()
                .map(medicalHistory -> modelMapper.map(medicalHistory, MedicalHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDTO getOneMedicalHistory(Long medicalHistoryId) {
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(medicalHistoryId)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(medicalHistory, MedicalHistoryDTO.class);
    }
}
