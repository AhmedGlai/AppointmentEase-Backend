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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Secured({"ROLE_ADMIN", "ROLE_PATIENT"})
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IEmailService emailService;

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.getAppointments(), HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getOneAppointment(@PathVariable Long appointmentId) {
        return new ResponseEntity<>(appointmentService.getOneAppointment(appointmentId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody @NotNull AppointmentDTO appointmentDTO) {
        AppointmentDTO savedAppointment = appointmentDTO;
        savedAppointment.setStatusAPT(StatusAPT.PENDING);
        savedAppointment = appointmentService.saveAppointment(appointmentDTO);

        if (savedAppointment.getStatusAPT() == StatusAPT.PENDING) {


            // Send email to doctor
            DoctorDTO doctor = doctorService.getOneDoctor(savedAppointment.getDoctor().getId());
            String recipient = doctor.getEmail();
            String subject = "New Appointment";
            String sender = "Hospital Support <support@hospital.com>";
            String body = "A new appointment has been created and is waiting for your approval. " +
                    "Please log in to the dashboard to accept or decline the appointment.";
            String url = "http://localhost:8080/dashboard?appointmentId=" + savedAppointment.getId();
            Email email = new Email(subject, recipient, sender, body, url);
            emailService.sendEmail(email);
        }
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);


    }


    @PutMapping("/update")
    public ResponseEntity<AppointmentDTO> editAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return new ResponseEntity<>(appointmentService.editAppointment(appointmentDTO), HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}/updateStatus")
    public ResponseEntity<AppointmentDTO>changeStatus(@PathVariable Long appointmentId,
                                                    @RequestParam StatusAPT status) {
       AppointmentDTO appointmentDTO = appointmentService.changeStatus(appointmentId, status);
       String message = "Your appointment with id "+appointmentId+"has been"+status.name().toLowerCase();
       Email email = new Email();
       email.setRecipient(appointmentDTO.getPatient().getEmail());
       email.setSubject("Appointment Status Update");
       email.setBody(message);

        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getPatientAppointments(@PathVariable Long patientId) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>>getDoctorAppointments(@PathVariable Long doctorId) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByDoctorId(doctorId), HttpStatus.OK);
    }
    @GetMapping("/approved-appointments/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getUpcomingPatientAppointments(@PathVariable Long patientId) {
        PatientDTO patientDTO = patientService.getOnePatient(patientId);
        List<AppointmentDTO> appointments = appointmentService.getUpcomingPatientAppointments(patientDTO);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AppointmentDTO>> searchAppointments(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String doctorName) {
        List<AppointmentDTO> appointments = appointmentService.searchAppointments(patientName, doctorName);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody Email notificationEmail) {
        emailService.sendEmail(notificationEmail);
        return ResponseEntity.ok("Email sent successfully");
    }


}
