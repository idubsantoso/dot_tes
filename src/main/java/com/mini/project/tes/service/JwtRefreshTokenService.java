package com.mini.project.tes.service;

import com.mini.project.tes.model.dto.JwtRefreshToken;
import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;

import java.util.Optional;

public interface JwtRefreshTokenService {
	public JwtRefreshToken findByUserId(long userId);
	public JwtRefreshTokenEntity findByUserIdAndAccessToken(Long userId, String accessToken);
	public Optional<JwtRefreshToken> findById(String id);
	public JwtRefreshToken saveJwt(JwtRefreshToken jwt);
	public boolean deleteJwt(JwtRefreshToken jwt);
	public JwtRefreshToken findByAccessToken(String accessToken);
}
