package com.mini.project.tes.service;

import com.mini.project.tes.config.security.UserPrincipal;
import com.mini.project.tes.model.dto.JwtRefreshToken;

public interface JwtService {
    public boolean saveRefreshToken(UserPrincipal userPrincipal, String refreshToken, JwtRefreshToken isExist, String accessToken);
}
