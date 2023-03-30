package com.spring.hospital.service;

import com.spring.hospital.dto.AppointmentDTO;
import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.ennumeration.StatusAPT;
import com.spring.hospital.entity.Appointment;
import com.spring.hospital.entity.Patient;

import java.util.List;

public interface IAppointmentService {

    /* Basic Crud */
    AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO editAppointment(AppointmentDTO appointmentDTO);
    void deleteAppointment(Long appointmentId);
    List<AppointmentDTO> getAppointments();
    AppointmentDTO getOneAppointment(Long appointmentId);

    /* Other Features */
    List<AppointmentDTO> getUpcomingPatientAppointments(PatientDTO patientDTO);
    List<AppointmentDTO> getPendingPatientAppointments(PatientDTO patientDTO);
    AppointmentDTO changeStatus (Long appointmentId, StatusAPT status );
    List<AppointmentDTO> getAppointmentsByPatientId(Long patientId);
    List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId);
    List<AppointmentDTO> searchAppointments(String patientName, String doctorName);



}
