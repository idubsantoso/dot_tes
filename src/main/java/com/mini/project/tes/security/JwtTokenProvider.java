package com.mini.project.tes.security;

import com.mini.project.tes.domain.JwtRefreshTokenEntity;
import com.mini.project.tes.persistence.service.JwtRefreshTokenService;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${spring.jwt.client-secret}")
    private String jwtSecret;
    @Value("${spring.jwt.expiredTimeToken}")
    private int jwtExpiredToken;

    @Autowired
    private JwtRefreshTokenService jwtTokenService;

    public String generateToken(UserPrincipal userPrincipal) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String generateRefreshToken() {
        // generate a random UUID as refresh token
        return UUID.randomUUID().toString();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
//        	Date timeLastAction = new Date();
//        	lastAction.remove(getUserIdFromJWT(authToken));
//        	lastAction.put(getUserIdFromJWT(authToken), timeLastAction);

        	JwtRefreshTokenEntity jwtEnt = jwtTokenService.findByUserId(getUserIdFromJWT(authToken));
        	if(jwtEnt==null){
        	    return false;
        	}else {
        	    if (!jwtEnt.getAccessToken().equals(authToken)) { 
        	    	return false;
        	    }
        	}
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	JwtAuthenticationEntryPoint.messErr = "Expired JWT token";
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
    public void invalidateToken(JwtRefreshTokenEntity refreshToken) {
        try {
            jwtTokenService.deleteJwt(refreshToken);
        } catch (Exception e) {
            logger.error(e.getMessage());
            //;e.printStackTrace();
        }
    }
}
