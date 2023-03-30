package com.spring.hospital.repository;

import com.spring.hospital.entity.Consultation;
import com.spring.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findByName(String name);

}
