package com.mini.project.tes.repository;


import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshTokenEntity, String> {

	JwtRefreshTokenEntity findByUserId(long userId);
	JwtRefreshTokenEntity findByAccessToken(String Token);
	
	JwtRefreshTokenEntity findByUserIdAndAccessToken(Long userId, String accessToken);

}
