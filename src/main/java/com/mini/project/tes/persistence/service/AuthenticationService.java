package com.mini.project.tes.persistence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.security.entity.JwtAuthenticationResponse;
import com.mini.project.tes.security.entity.LoginRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse authenticate(LoginRequest loginRequest, String ipAddress, String remoteHost) throws Exception;

    void logout(String bearerToken, String ipAddress, String remoteHost);

    JwtAuthenticationResponse refreshAccessToken(String refreshToken, String ipAddress, String remoteHost) throws JsonProcessingException;

}
