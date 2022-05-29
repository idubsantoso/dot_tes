package com.mini.project.tes.service.impl;

import com.mini.project.tes.assembler.JwtRefreshTokenAssembler;
import com.mini.project.tes.model.dto.JwtRefreshToken;
import com.mini.project.tes.model.entity.JwtRefreshTokenEntity;
import com.mini.project.tes.repository.JwtRefreshTokenRepository;
import com.mini.project.tes.service.JwtRefreshTokenService;
import com.mini.project.tes.service.error.ServiceException;
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

	@Autowired
	private JwtRefreshTokenAssembler assembler;

	@Override
	public JwtRefreshToken findByUserId(long userId) {
		try {
			return assembler.convertToDto(repo.findByUserId(userId)) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Optional<JwtRefreshToken> findById(String id) {
		try {
			Optional<JwtRefreshTokenEntity> jwtRefreshTokenEntity = repo.findById(id);
			Optional<JwtRefreshToken> jwtRefreshToken = jwtRefreshTokenEntity.isPresent() ? Optional.of( assembler.convertToDto(jwtRefreshTokenEntity.get())) : Optional.empty();
			return jwtRefreshToken;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public JwtRefreshToken saveJwt(JwtRefreshToken jwt) {
		try {
			return assembler.convertToDto(repo.save(assembler.convertToEntity(jwt)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean deleteJwt(JwtRefreshToken jwt) {
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
	public JwtRefreshToken findByAccessToken(String token) {
		try {
			return assembler.convertToDto(repo.findByAccessToken(token)) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
