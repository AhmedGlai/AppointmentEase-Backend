package com.spring.hospital.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<DoctorDTO> doctors;
}

