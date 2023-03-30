package com.spring.hospital.repository;

import com.spring.hospital.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {


}
