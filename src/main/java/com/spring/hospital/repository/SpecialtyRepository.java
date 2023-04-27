package com.spring.hospital.repository;

import com.spring.hospital.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty,Long> {
}
