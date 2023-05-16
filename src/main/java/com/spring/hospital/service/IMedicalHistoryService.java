package com.spring.hospital.service;

import com.spring.hospital.dto.MedicalHistoryDTO;

import java.util.List;

public interface IMedicalHistoryService {
    MedicalHistoryDTO saveMedicalHistory(MedicalHistoryDTO medicalHistoryDTO);
    MedicalHistoryDTO editMedicalHistory(MedicalHistoryDTO medicalHistoryDTO);
    void deleteMedicalHistory(Long medicalHistoryId);
    List<MedicalHistoryDTO> getMedicalHistories();
    MedicalHistoryDTO getOneMedicalHistory(Long medicalHistoryId);
}
