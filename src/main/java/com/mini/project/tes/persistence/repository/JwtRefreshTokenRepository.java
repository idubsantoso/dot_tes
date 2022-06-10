package com.mini.project.tes.persistence.repository;


import com.mini.project.tes.domain.JwtRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshTokenEntity, String> {

	JwtRefreshTokenEntity findByUserId(long userId);
	JwtRefreshTokenEntity findByAccessToken(String Token);
	
	JwtRefreshTokenEntity findByUserIdAndAccessToken(Long userId, String accessToken);

}
