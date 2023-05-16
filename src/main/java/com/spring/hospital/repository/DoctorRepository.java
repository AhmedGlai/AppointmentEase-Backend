package com.spring.hospital.repository;

import com.spring.hospital.entity.Consultation;
import com.spring.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByFullName(String name);
    List<Doctor> findBySpecialtyId(@Param("specialtyId") Long specialtyId);

}
