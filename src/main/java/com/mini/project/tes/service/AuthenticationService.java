package com.mini.project.tes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.model.dto.JwtAuthenticationResponse;
import com.mini.project.tes.model.dto.LoginRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse authenticate(LoginRequest loginRequest, String ipAddress, String remoteHost) throws Exception;

    void logout(String bearerToken, String ipAddress, String remoteHost);

    JwtAuthenticationResponse refreshAccessToken(String refreshToken, String ipAddress, String remoteHost) throws JsonProcessingException;

}
