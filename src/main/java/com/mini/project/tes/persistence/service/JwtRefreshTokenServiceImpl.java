package com.mini.project.tes.persistence.service;

import com.mini.project.tes.domain.JwtRefreshTokenEntity;
import com.mini.project.tes.persistence.repository.JwtRefreshTokenRepository;
import com.mini.project.tes.persistence.service.error.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JwtRefreshTokenRepository repo;

	@Override
	public JwtRefreshTokenEntity findByUserId(long userId) {
		try {
			return repo.findByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Optional<JwtRefreshTokenEntity> findById(String id) {
		try {
			Optional<JwtRefreshTokenEntity> jwtRefreshTokenEntity = repo.findById(id);
//			Optional<JwtRefreshToken> jwtRefreshToken = jwtRefreshTokenEntity.isPresent() ? Optional.of( assembler.convertToDto(jwtRefreshTokenEntity.get())) : Optional.empty();
			return jwtRefreshTokenEntity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public JwtRefreshTokenEntity saveJwt(JwtRefreshTokenEntity jwt) {
		try {
			JwtRefreshTokenEntity entity=repo.save(jwt);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean deleteJwt(JwtRefreshTokenEntity jwt) {
		try {
			repo.deleteById(jwt.getToken());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public JwtRefreshTokenEntity findByUserIdAndAccessToken(Long userId, String accessToken) {
		// TODO Auto-generated method stub
		try {JwtRefreshTokenEntity entity = repo.findByUserIdAndAccessToken(userId, accessToken);
			return entity;
		} catch (Exception e) {
            log.error("Error find by Token - "+ userId.toString() + " in " + this.getClass().getName() + " : " , e);
            throw new  ServiceException(e.getMessage());
        }
	}
	@Override
	public JwtRefreshTokenEntity findByAccessToken(String token) {
		try {
			return repo.findByAccessToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
