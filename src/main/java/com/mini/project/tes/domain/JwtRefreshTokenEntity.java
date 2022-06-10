package com.mini.project.tes.domain;

import com.mini.project.tes.security.entity.SamUserEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@Table(name = "jwt_refresh_token")
public class JwtRefreshTokenEntity {
	@Id
    private String token;
	
	@Column(name = "access_token", nullable = true)
    private String accessToken;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false,unique = true)
    private SamUserEntity user;

    private Instant expirationDateTime;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date loginTime;

    private String loginIp;
    private String workgroup;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastRefresh;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date createdDate;

    public JwtRefreshTokenEntity() {

    }

	public JwtRefreshTokenEntity(String token) {
        this.token = token;
    }

    
}