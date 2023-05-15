package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "doctor_name")
    private String name;
    @Column(name = "doctor_email",unique = true)
    private String email;

    private String speciality;
    @Column(name = "doctor_phone",unique = true)
    private String phone;
    @OneToMany(mappedBy="doctor",fetch = FetchType.LAZY)
    private Collection<Appointment> appointments;

}
