package com.spring.hospital.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        private String speciality;
        private String phone;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Collection<AppointmentDTO> appointments;
    }

