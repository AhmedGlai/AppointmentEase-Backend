package com.spring.hospital.dto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ConsultationDTO {
        private Long id;
        private Date dateConsultation;
        private String rapport;
        private AppointmentDTO appointmentId;
    }


