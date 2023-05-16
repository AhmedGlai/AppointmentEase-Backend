package com.spring.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDTO{
    private Long id;
    private String name;
    private String description;

    private Collection<DoctorDTO> doctors;
}

