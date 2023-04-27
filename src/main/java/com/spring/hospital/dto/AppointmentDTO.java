package com.spring.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spring.hospital.ennumeration.StatusAPT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class AppointmentDTO {
        private Long id;
        private LocalDate date;
        private String reason;
        private StatusAPT statusAPT;
        private DoctorDTO doctor;
        private PatientDTO patient;


    }

