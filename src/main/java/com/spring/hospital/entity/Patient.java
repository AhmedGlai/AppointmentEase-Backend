package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "patient_name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @Column(name = "patient_email")
    private String email;

    @Column(name = "patient_address")
    private String address;


    private String gender;

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    @JsonManagedReference("patient")
    private Collection<Appointment> appointments;


    @OneToOne(cascade = CascadeType.ALL)
    private MedicalHistory medicalHistory;
}
