package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.*;
import com.spring.hospital.ennumeration.StatusAPT;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private StatusAPT statusAPT;

    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("doctor")
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("patient")
    @JoinColumn(name="patient_id")
    private Patient patient;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @JsonBackReference("appointment")
    private Consultation consultation;




}
