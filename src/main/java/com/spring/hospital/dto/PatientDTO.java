package com.spring.hospital.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spring.hospital.ennumeration.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String address;
    private Gender gender;
    private String phone;
    private MedicalHistoryDTO medicalHistory;
    private Long userId;





}