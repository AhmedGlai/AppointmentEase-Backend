package com.spring.hospital.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private String message;

    public AuthenticationResponse(String message) {
        this.message=message;
    }
}
