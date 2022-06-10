package com.mini.project.tes.persistence.service;

import com.mini.project.tes.config.security.UserPrincipal;
import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;

public interface JwtService {
    public boolean saveRefreshToken(UserPrincipal userPrincipal, String refreshToken, JwtRefreshTokenEntity isExist, String accessToken);
}
