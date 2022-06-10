package com.mini.project.tes.persistence.service;


import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;
import com.mini.project.tes.persistence.repository.SamUserRepository;
import com.mini.project.tes.config.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
