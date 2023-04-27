package com.spring.hospital.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DoctorDTO {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String address;
        private SpecialtyDTO specialty;
    }

