package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.hospital.ennumeration.Gender;
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
@Table(name="tbl_Doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "doctor_name")
    private String fullName;
    @Column(name = "birth_date")
    private Date dateOfBirth;
    @Column(name = "doctor_email",unique = true)
    private String email;
    @Column(name = "doctor_address")
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "doctor_phone",unique = true)
    private String phone;
    @ManyToOne(fetch = FetchType.LAZY)

    @JsonBackReference("specialty")
    private Specialty specialty;
    @OneToMany(mappedBy="doctor",fetch = FetchType.LAZY)
    private Collection<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}