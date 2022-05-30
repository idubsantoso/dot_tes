package com.mini.project.tes.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.project.tes.config.security.JwtAuthenticationFilter;
import com.mini.project.tes.config.security.JwtTokenProvider;
import com.mini.project.tes.model.dto.*;
import com.mini.project.tes.model.entity.ApiResponse;
import com.mini.project.tes.service.*;
import com.mini.project.tes.service.impl.AuthenticationServiceImpl;
import com.mini.project.tes.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Hashtable;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRest {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    JwtRefreshTokenService jwtTokenService;

    @Autowired
    JwtAuthenticationFilter jwtfilter;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;


    @Value("${spring.jwt.accessTokenValidititySeconds}")
    private long jwtExpirationInMs;


    @SuppressWarnings({"unchecked", "rawtypes", "serial"})
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request, @Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException, Exception {

        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.authenticate(loginRequest, request.getRemoteAddr(), request.getRemoteHost());

        return ResponseEntity.ok(jwtAuthenticationResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authenticationService.logout(request.getHeader("Authorization"), request.getRemoteAddr(), request.getRemoteHost());
        //tokenProvider.invalidateToken(request);
        return new ResponseEntity(new ApiResponse(true, "Success Logout"), HttpStatus.OK);
    }




    @SuppressWarnings("unchecked")
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request, @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws JsonProcessingException {

        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.refreshAccessToken(refreshTokenRequest.getRefreshToken(), request.getRemoteAddr(), request.getRemoteHost());
//        String token=getJwtFromRequest(request);
        return ResponseEntity.ok(jwtAuthenticationResponse);
    }


    @GetMapping("/isTokenExist")
    public ResponseEntity<Object> checkTokenIsExist(HttpServletRequest request){
        try{
            String jwt = getJwtFromRequest(request);
            if(jwtTokenService.findByAccessToken(jwt) != null){
                return new ResponseEntity(new ApiResponse((true), "Token is exist"),HttpStatus.OK);
            }else{
                return new ResponseEntity(new ApiResponse((true), "Token not exist"),HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(new ApiResponse((false), "Error check token"),HttpStatus.OK);
        }
    }


    protected String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
