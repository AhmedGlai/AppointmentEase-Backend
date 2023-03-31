package com.spring.hospital.service.Implementation;

import com.spring.hospital.dto.AuthenticationRequest;
import com.spring.hospital.dto.AuthenticationResponse;
import com.spring.hospital.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        return  null;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return null;
    }
}
