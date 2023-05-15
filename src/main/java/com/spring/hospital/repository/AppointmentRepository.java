package com.spring.hospital.repository;

import com.spring.hospital.dto.AppointmentDTO;
import com.spring.hospital.ennumeration.StatusAPT;
import com.spring.hospital.entity.Appointment;
import com.spring.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByPatientAndStatusAPTOrderByDateAsc(Patient patient, StatusAPT statusAPT);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDoctorNameContainingIgnoreCase(String doctorName);
    List<Appointment> findByPatientNameContainingIgnoreCase(String patientName);
    List<Appointment> findByDate(Date appointmentDate);
    List<Appointment>findByDoctorNameContainingIgnoreCaseAndPatientNameContainingIgnoreCase(String doctorName,String patientName);


}
