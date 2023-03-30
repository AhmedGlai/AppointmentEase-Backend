package com.spring.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {
    private Long id ;
    private String rapport;
    private Patient patient;

}
