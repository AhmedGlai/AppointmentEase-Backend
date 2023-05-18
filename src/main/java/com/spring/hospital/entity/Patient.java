package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.hospital.ennumeration.Gender;
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
    private String fullName;

    @Column(name = "birth_date")
    private Date dateOfBirth;

    @Column(name = "patient_email",unique = true)
    private String email;

    @Column(name = "patient_address")
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "patient_phone",unique = true)
    private String phone;


    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    @JsonManagedReference("patient")
    private Collection<Appointment> appointment;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "medicalHistoryId")
    private MedicalHistory medicalHistory;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
