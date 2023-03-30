package com.spring.hospital.dto;

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
        private StatusAPT statusAPT;
        private DoctorDTO doctor;
        private PatientDTO patient;
        private ConsultationDTO consultation;


    }

