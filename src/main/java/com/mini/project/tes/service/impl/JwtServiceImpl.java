package com.mini.project.tes.service.impl;


import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;
import com.mini.project.tes.repository.SamUserRepository;
import com.mini.project.tes.config.security.UserPrincipal;
import com.mini.project.tes.model.dto.JwtRefreshToken;
import com.mini.project.tes.service.JwtRefreshTokenService;
import com.mini.project.tes.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Transactional
@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private SamUserRepository samUserService;

    @Autowired
    JwtRefreshTokenService jwtTokenService;

    @Override
    public boolean saveRefreshToken(UserPrincipal userPrincipal, String refreshToken, JwtRefreshTokenEntity isExist, String accessToken) {
    	Date today = new Date();
        if(isExist!=null) {
//        	jwtTokenService.deleteJwt(isExist);
            isExist.setAccessToken(accessToken);
            isExist.setCreatedDate(today);
            isExist.setUser(samUserService.getById(userPrincipal.getId()));
            jwtTokenService.saveJwt(isExist);
        }
        else {
            JwtRefreshTokenEntity jwtRefreshToken = new JwtRefreshTokenEntity(refreshToken);
            jwtRefreshToken.setAccessToken(accessToken);
            jwtRefreshToken.setCreatedDate(today);
            jwtRefreshToken.setUser(samUserService.getById(userPrincipal.getId()));
            jwtTokenService.saveJwt(jwtRefreshToken);
        }
        return false;
    }
}
