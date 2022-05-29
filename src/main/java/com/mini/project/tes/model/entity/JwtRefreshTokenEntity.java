package com.mini.project.tes.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "session_user")
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public SamUserEntity getUser() {
        return user;
    }

    public void setUser(SamUserEntity user) {
        this.user = user;
    }

    public Instant getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(Instant expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
}