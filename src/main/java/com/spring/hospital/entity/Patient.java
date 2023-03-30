package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "patient_name",nullable = false)
    private String name;

    //@Temporal(TemporalType.DATE)
    @Column(name = "patient_date",nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "patient_email")
    private String email;

    @Column(name = "patient_address")
    private String address;
    @Column(nullable = false)
    private String gender;
    private boolean sick;
    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)

    private Collection<Appointment> appointments;


}
