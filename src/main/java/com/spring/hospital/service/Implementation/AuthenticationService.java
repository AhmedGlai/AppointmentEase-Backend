package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.AuthenticationRequest;
import com.spring.hospital.dto.AuthenticationResponse;
import com.spring.hospital.dto.RegisterRequest;
import com.spring.hospital.ennumeration.UserRole;
import com.spring.hospital.entity.Doctor;
import com.spring.hospital.entity.Patient;
import com.spring.hospital.entity.Specialty;
import com.spring.hospital.entity.User;
import com.spring.hospital.repository.DoctorRepository;
import com.spring.hospital.repository.PatientRepository;
import com.spring.hospital.repository.SpecialtyRepository;
import com.spring.hospital.repository.UserRepository;
import com.spring.hospital.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    // Add DoctorRepository and PatientRepository dependencies here
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    //Image Storage Service
    private final ImageStorageService imageStorageService;

    private final SpecialtyRepository specialtyRepository;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var emailExists = userRepository.findByEmail(registerRequest.getEmail());
        if (emailExists.isPresent()) {
            throw new DuplicateKeyException("This email address is already registered. Please try again with a different email.");
        }

        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .userRole(registerRequest.getRole())

                .build();
        // Store the profile image for the user
        if (registerRequest.getProfileImage() != null && !registerRequest.getProfileImage().isEmpty()) {
            String fileName = imageStorageService.storeProfileImage(registerRequest.getProfileImage());
            user.setProfileImage(fileName);
        }

        // Save the user
        userRepository.save(user);

        // Check the user role and save the corresponding entity
        if (registerRequest.getRole() == UserRole.DOCTOR) {
            Doctor doctor = new Doctor();
            doctor.setFullName(registerRequest.getFullName());
            doctor.setEmail(registerRequest.getEmail());
            doctor.setAddress(registerRequest.getAddress());
            doctor.setGender(registerRequest.getGender());
            doctor.setPhone(registerRequest.getPhone());
            doctor.setDateOfBirth(parseDate(registerRequest.getDateOfBirth(), "yyyy-MM-dd"));
            doctor.setUser(user);
            // Retrieve the Specialty entity using the specialtyName
            Specialty specialty = specialtyRepository.findByName(registerRequest.getSpecialtyName())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid specialty name"));

            // Associate the retrieved Specialty with the Doctor
            doctor.setSpecialty(specialty);
            // Save the doctor
            doctorRepository.save(doctor);
        } else if (registerRequest.getRole() == UserRole.PATIENT) {
            Patient patient = new Patient();
            patient.setFullName(registerRequest.getFullName());
            patient.setEmail(registerRequest.getEmail());
            patient.setAddress(registerRequest.getAddress());
            patient.setGender(registerRequest.getGender());
            patient.setPhone(registerRequest.getPhone());
            patient.setDateOfBirth(parseDate(registerRequest.getDateOfBirth(), "yyyy-MM-dd"));
            patient.setUser(user);
            // Save the patient
            patientRepository.save(patient);
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    /*Parse email from String to Date */
    private Date parseDate(String dateString, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
