package com.mini.project.tes.persistence.service;

import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;

import java.util.Optional;

public interface JwtRefreshTokenService {
	public JwtRefreshTokenEntity findByUserId(long userId);
	public JwtRefreshTokenEntity findByUserIdAndAccessToken(Long userId, String accessToken);
	public Optional<JwtRefreshTokenEntity> findById(String id);
	JwtRefreshTokenEntity saveJwt(JwtRefreshTokenEntity jwt);
	public boolean deleteJwt(JwtRefreshTokenEntity jwt);
	public JwtRefreshTokenEntity findByAccessToken(String accessToken);
}
