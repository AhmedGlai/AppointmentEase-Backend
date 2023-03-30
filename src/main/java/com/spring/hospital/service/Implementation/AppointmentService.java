package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.AppointmentDTO;
import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.ennumeration.StatusAPT;
import com.spring.hospital.entity.Appointment;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.repository.AppointmentRepository;
import com.spring.hospital.service.IAppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AppointmentService implements IAppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Override
    public AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return modelMapper.map(savedAppointment, AppointmentDTO.class);
    }


    @Override
    public AppointmentDTO editAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return modelMapper.map(updatedAppointment, AppointmentDTO.class);
    }
    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<AppointmentDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public AppointmentDTO getOneAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(appointment, AppointmentDTO.class);
    }


    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public AppointmentDTO changeStatus(Long appointmentId, StatusAPT status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new EntityNotFoundException("Appointment by ID: " + appointmentId + " not found"));
        appointment.setStatusAPT(status);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return modelMapper.map(updatedAppointment, AppointmentDTO.class);
    }


    @Override
    public List<AppointmentDTO> getUpcomingPatientAppointments(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        StatusAPT statusAPT = StatusAPT.APPROVED;
        List<Appointment> appointments = appointmentRepository.findByPatientAndStatusAPTOrderByDateAsc(patient, statusAPT);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<AppointmentDTO> getPendingPatientAppointments(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        StatusAPT statusAPT = StatusAPT.PENDING;
        List<Appointment> appointments = appointmentRepository.findByPatientAndStatusAPTOrderByDateAsc(patient, statusAPT);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<AppointmentDTO> searchAppointments(String patientName, String doctorName) {
        if (patientName == null && doctorName == null ) {
            List<Appointment> appointments = appointmentRepository.findAll();
            return appointments.stream()
                    .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                    .collect(Collectors.toList());
        }
        List<Appointment> appointments = appointmentRepository.findByDoctorNameContainingIgnoreCaseAndPatientNameContainingIgnoreCase(doctorName != null ? doctorName : "", patientName != null ? patientName : "" );
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
    }



}
