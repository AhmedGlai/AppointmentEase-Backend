package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_Consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="consultation_date")
    private Date dateConsultation;

    @Column(name = "rapport")
    private String rapport;

    @JsonManagedReference("consultation")
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}