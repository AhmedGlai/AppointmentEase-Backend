package com.spring.hospital.repository;

import com.spring.hospital.entity.Patient;
import com.spring.hospital.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {


    Optional<Specialty> findByName(String name);
}
