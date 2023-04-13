package com.spring.hospital.dto;

import com.spring.hospital.ennumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstname;
    private  String lastname;
    private String email;
    private  String password;
    private UserRole role;



}
