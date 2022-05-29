package com.mini.project.tes.service.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.project.tes.config.security.*;
import com.mini.project.tes.model.entity.SamUserEntity;
import com.mini.project.tes.repository.SamUserRepository;
import com.mini.project.tes.exception.InvalidTokenExeption;
import com.mini.project.tes.model.dto.*;
import com.mini.project.tes.service.*;
import com.mini.project.tes.util.BaseHelper;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

//@Transactional
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
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
    private SamUserRepository samUserService;


    @Autowired
    private JwtService jwtService;


    @Value("${spring.jwt.expiredTimeToken}")
    private int jwtExpiredToken;

    @Override
    public JwtAuthenticationResponse authenticate(LoginRequest loginRequest, String ipAddress, String remoteHost) throws Exception {
        String refreshToken = null;
        BaseHelper baseHelper =  new BaseHelper();
        JwtAuthenticationResponse jwtAuthenticationResponse = null;
        SamUserEntity user = samUserService.findByUsername(loginRequest.getUsernameOrEmail());
        if (user == null){
            JwtAuthenticationEntryPoint.messErr ="Login Failed. User Id or Password is incorrect";
            throw new BadCredentialsException("User Id or Password is incorrect");
        }

        if(loginRequest.getPassword()!=null) {
                if (!baseHelper.getPasswordMD5(loginRequest.getPassword()).equals(user.getPassword())) {
                        try {
                            int totalLoginAttempt = user.getLoginAttempt() == null ? (0 + 1) : user.getLoginAttempt() + 1;
                            user.setLoginAttempt(totalLoginAttempt);
                        } catch (Exception e) {
                            throw new Exception("Update attempt failed");
                        }
                    JwtAuthenticationEntryPoint.messErr = "Login Failed. User Id or Password is incorrect";
                    throw new BadCredentialsException("User Id Or Password incorrect");
                }
            }


//        System.out.println("User password verified");

        CustomAuthentication auth = new CustomAuthentication(loginRequest, user);
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String accessToken = tokenProvider.generateToken(userPrincipal);
        //check if tokenRefresh Exist
        JwtRefreshToken isExist = jwtTokenService.findByUserId(userPrincipal.getId());


        if (isExist == null) {
            refreshToken = tokenProvider.generateRefreshToken();
        } else {
            refreshToken = isExist.getToken();
        }


        //======== Update user login info and refresh login attempt =============================
        user.setLastLogIn(new Date());
        user.setLoginAttempt(0);
        samUserService.save(user);
        user.setPassword(baseHelper.encrypt(loginRequest.getPassword()));

        //=======================================================================================//

        jwtService.saveRefreshToken(userPrincipal, refreshToken, isExist, accessToken);


        return jwtAuthenticationResponse;
    }

    @Override
    //@Transactional
    public void logout(@NotNull String bearerToken, String ipAddress, String remoteHost){
        //String bearerToken = request.getHeader("Authorization");
        String token = bearerToken.substring(7, bearerToken.length());

        if (token==null) {
            JwtAuthenticationEntryPoint.messErr ="Your session has been expired";
            throw new BadCredentialsException("Sorry, You're not authorized to access this resource.");
        }
        JwtRefreshToken refreshToken = jwtTokenService.findByAccessToken(token);

        if (refreshToken!=null) {
        	tokenProvider.invalidateToken(refreshToken);
		}else {
            JwtAuthenticationEntryPoint.messErr ="User Already Logout";
            JwtAuthenticationEntryPoint.codeErr = HttpServletResponse.SC_FORBIDDEN;
            throw new BadCredentialsException("Sorry, You're not authorized to access this resource.");
        }
    }


    @Override
    public JwtAuthenticationResponse refreshAccessToken(String refreshToken, String ipAddress, String remoteHost) throws JsonProcessingException {
        JwtRefreshToken jwtRefreshToken = null;

        Optional<JwtRefreshToken> optionalJwtRefreshToken = jwtTokenService.findById(refreshToken);
        if(!optionalJwtRefreshToken.isPresent()){
            throw new InvalidTokenExeption("Refresh token is invalid");
        } else{
            jwtRefreshToken = optionalJwtRefreshToken.get();
        }

        SamUserEntity user = jwtRefreshToken.getUser();

        Instant expDate = jwtRefreshToken.getExpirationDateTime().plus(jwtExpiredToken+5, ChronoUnit.MINUTES);
        if(expDate.isBefore(Instant.now())){
            jwtTokenService.deleteJwt(jwtRefreshToken);
//            activityLogService.createProcessLog(user,2, 2, ipAddress, remoteHost);
            throw new InvalidTokenExeption("Refresh token is expired");
        }

        String accessToken = tokenProvider.generateToken(UserPrincipal.create(user));

        //UserPrincipal userprincipal = new UserPrincipal(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        jwtService.saveRefreshToken(userPrincipal, refreshToken, jwtRefreshToken, accessToken);



        return new JwtAuthenticationResponse(accessToken, jwtRefreshToken.getToken());
    }


    private class CustomAuthentication implements Authentication {
        private  LoginRequest loginRequest;
        private SamUserEntity user;

        public  CustomAuthentication(LoginRequest loginRequest, SamUserEntity user){
            this.loginRequest = loginRequest;
            this.user = user;
        }

        @Override
        public String getName() {
            return loginRequest.getUsernameOrEmail();
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public Object getPrincipal() {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            //TODO authority
            //UserPrincipal userprincipal = new UserPrincipal(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities, loginRequest.getLoginIp(), loginRequest.getWorkGroup());
            return UserPrincipal.create(user);
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
//            List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
//                    new SimpleGrantedAuthority(role.getRoleName())
//            ).collect(Collectors.toList());
            return null;
        }
    }
}
