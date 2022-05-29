package com.mini.project.tes.config.security;

import com.permata.recurring.core.model.dto.JwtRefreshToken;
import com.permata.recurring.core.service.JwtRefreshTokenService;
import com.permata.recurring.core.service.SecurityParameterService;
import com.permata.recurring.core.util.UserUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Winner [Alpabit]
 * <p>
 * Dec 4, 2019
 */
@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${spring.jwt.client-secret}")
    private String jwtSecret;
    @Value("${spring.jwt.expiredTimeToken}")
    private int jwtExpiredToken;

    @Autowired
    private JwtRefreshTokenService jwtTokenService;
    @Autowired
    private SecurityParameterService securityParameterService;
    @Autowired
    private UserUtil userUtil;

    //private JwtAuthenticationFilter jwtFilter;
//    public static HashMap<Long, Date> lastAction = new HashMap<>();

    public String generateToken(UserPrincipal userPrincipal) {
        Date now = new Date();
//        System.out.println(securityParameterService.findAll().get(0).getMinuteExpiredLogin()*60*1000);
        Date expiryDate = new Date(now.getTime()+(securityParameterService.findAll().get(0).getMinuteExpiredLogin()+jwtExpiredToken)*60*1000);
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String generateTokenForEngine(UserDetails userPrincipal) {
        Date now = new Date();
        Map<String, Object> claims = new HashMap<>();
        Date expiryDate = new Date(now.getTime()+(securityParameterService.findAll().get(0).getMinuteExpiredLogin()+jwtExpiredToken)*60*1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
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

        	JwtRefreshToken jwtEnt = jwtTokenService.findByUserId(getUserIdFromJWT(authToken));
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

    /*
    public void invalidateToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token = bearerToken.substring(6, bearerToken.length());
        try {
            JwtRefreshToken refreshToken = jwtTokenService.findByUserId(getUserIdFromJWT(token));
            User userOptional = samUserService.findById(getUserIdFromJWT(token));
            userOptional.setLastLogOut(new Date());

            samUserService.update(userOptional);
            jwtTokenService.deleteJwt(refreshToken);
        } catch (Exception e) {
            logger.error(e.getMessage());
            //;e.printStackTrace();
        }
    }

     */

    public void invalidateTokenByUserId(Long userId) {
        try {
            JwtRefreshToken refreshToken = jwtTokenService.findByUserId(userId);
            //delete all static variable from user util
            jwtTokenService.deleteJwt(refreshToken);
            userUtil.userEntityLogin.remove(userId);
            userUtil.unitIdUserLogin.remove(userId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            //;e.printStackTrace();
        }
    }
    
    public void invalidateToken(JwtRefreshToken refreshToken) {
        try {
            jwtTokenService.deleteJwt(refreshToken);
            long id = getUserIdFromJWT(refreshToken.getAccessToken());
            userUtil.userEntityLogin.remove(id);
            userUtil.unitIdUserLogin.remove(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            //;e.printStackTrace();
        }
    }
}
