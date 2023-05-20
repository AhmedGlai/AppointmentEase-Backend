package com.spring.hospital.controller;

import com.spring.hospital.dto.AppointmentDTO;
import com.spring.hospital.dto.DoctorDTO;
import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.ennumeration.StatusAPT;
import com.spring.hospital.entity.Appointment;
import com.spring.hospital.entity.Doctor;
import com.spring.hospital.entity.Email;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.service.IAppointmentService;
import com.spring.hospital.service.IDoctorService;
import com.spring.hospital.service.IEmailService;
import com.spring.hospital.service.IPatientService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PostAuthorize;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private IDoctorService doctorService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IEmailService emailService;

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT"})
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.getAppointments(), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getOneAppointment(@PathVariable Long appointmentId) {
        return new ResponseEntity<>(appointmentService.getOneAppointment(appointmentId), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody @NotNull AppointmentDTO appointmentDTO) {
        AppointmentDTO savedAppointment = appointmentDTO;
        savedAppointment.setStatusAPT(StatusAPT.PENDING);
        savedAppointment = appointmentService.saveAppointment(appointmentDTO);

        if (savedAppointment.getStatusAPT() == StatusAPT.PENDING) {
            DoctorDTO doctor = doctorService.getOneDoctor(savedAppointment.getDoctor().getId());

            if (doctor != null && doctor.getEmail() != null) {
                LocalDate lastAppointmentDate = appointmentService.getLastAppointmentDateByDoctor(doctor.getId());
                LocalDate appointmentCreationDate = savedAppointment.getDate();

                // Check if appointmentCreationDate is before lastAppointmentDate
                if (lastAppointmentDate != null && appointmentCreationDate != null
                        && appointmentCreationDate.isBefore(lastAppointmentDate.atStartOfDay().toLocalDate())) {
                    // Send email to doctor
                    String recipient = doctor.getEmail();
                    String subject = "New Appointment";
                    String sender = "Hospital Support <support@hospital.com>";
                    String body = "A new appointment has been created and is waiting for your approval. " +
                            "Please log in to the dashboard to accept or decline the appointment.";
                    String url = "http://localhost:8080/dashboard?appointmentId=" + savedAppointment.getId();
                    Email email = new Email(subject, recipient, sender, body, url);
                    emailService.sendEmail(email);
                }
            }
        }
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }


    @Secured({"ROLE_ADMIN", "ROLE_PATIENT"})
    @PutMapping("/update")
    public ResponseEntity<AppointmentDTO> editAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return new ResponseEntity<>(appointmentService.editAppointment(appointmentDTO), HttpStatus.OK);
    }

    @Secured({"ROLE_DOCTOR"})
    @PutMapping("/{appointmentId}/updateStatus")
    public ResponseEntity<AppointmentDTO>changeStatus(@PathVariable Long appointmentId,
                                                    @RequestParam StatusAPT status) {
       AppointmentDTO appointmentDTO = appointmentService.changeStatus(appointmentId, status);
       String message = "Your appointment with id "+appointmentId+" has been "+status.name().toLowerCase();
       Email email = new Email();
       email.setRecipient(appointmentDTO.getPatient().getEmail());
       email.setSubject("Appointment Status Update");
       email.setBody(message);

        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointments(@PathVariable Long patientId) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatientId(patientId), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>>getDoctorAppointments(@PathVariable Long doctorId) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByDoctorId(doctorId), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/approved-appointments/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getUpcomingPatientAppointments(@PathVariable Long patientId) {
        PatientDTO patientDTO = patientService.getOnePatient(patientId);
        List<AppointmentDTO> appointments = appointmentService.getUpcomingPatientAppointments(patientDTO);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @GetMapping("/search")
    public ResponseEntity<List<AppointmentDTO>> searchAppointments(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String doctorName) {
        List<AppointmentDTO> appointments = appointmentService.searchAppointments(patientName, doctorName);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_PATIENT","ROLE_DOCTOR"})
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody Email notificationEmail) {
        emailService.sendEmail(notificationEmail);
        return ResponseEntity.ok("Email sent successfully");
    }




}
