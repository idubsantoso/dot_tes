package com.mini.project.tes.persistence.service;

import com.mini.project.tes.security.UserPrincipal;
import com.mini.project.tes.domain.JwtRefreshTokenEntity;

public interface JwtService {
    public boolean saveRefreshToken(UserPrincipal userPrincipal, String refreshToken, JwtRefreshTokenEntity isExist, String accessToken);
}
