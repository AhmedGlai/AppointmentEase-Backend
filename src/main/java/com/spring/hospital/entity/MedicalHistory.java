package com.spring.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_MedicalHistory")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(name = "allergies")
    private String allergies;

    @OneToOne(mappedBy = "medicalHistory", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("medicalHistoryId")
    private Patient patient;

}
