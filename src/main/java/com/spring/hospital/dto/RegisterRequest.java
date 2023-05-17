package com.spring.hospital.dto;

import com.spring.hospital.ennumeration.Gender;
import com.spring.hospital.ennumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private  String fullName;
    private String dateOfBirth;
    private String email;
    private  String password;
    private UserRole role;
    private String address;
    private Gender gender;
    private String phone;
    /*Image*/
    private MultipartFile profileImage;
    /*Doctor Related only*/
    private String specialtyName;



}
