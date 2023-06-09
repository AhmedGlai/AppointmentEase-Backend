package com.spring.hospital.dto;

import lombok.Data;

@Data
public class MedicalHistoryDTO {
    private Long id;
    private Double height;
    private Double weight;
    private String bloodPressure;
    private String allergies;
    private String surgeries;
    private String medications;
    private String familyMedicalHistory;
    private String immunizationRecords;

}
