package com.spring.hospital.repository;

import com.spring.hospital.dto.AppointmentDTO;
import com.spring.hospital.ennumeration.StatusAPT;
import com.spring.hospital.entity.Appointment;
import com.spring.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByPatientAndStatusAPTOrderByDateAsc(Patient patient, StatusAPT statusAPT);
    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByDate(LocalDate appointmentDate);
    List<Appointment>findByDoctor_FullNameContainingIgnoreCaseAndPatient_FullNameContainingIgnoreCase(String doctorName,String patientName);

    Optional<Appointment> findTopByDoctorIdOrderByDateDesc(Long doctorId);
}
