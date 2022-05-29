package com.mini.project.tes.repository;

/**
 * @author Winner [Alpabit]
 *
 * Dec 4, 2019
 */

import com.permata.recurring.core.model.entity.JwtRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshTokenEntity, String> {

	JwtRefreshTokenEntity findByUserId(long userId);
	JwtRefreshTokenEntity findByAccessToken(String Token);
	
	JwtRefreshTokenEntity findByUserIdAndAccessToken(Long userId, String accessToken);

}
