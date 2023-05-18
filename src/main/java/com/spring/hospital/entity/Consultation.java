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


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    @JsonManagedReference("appointment")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Appointment appointment;
}