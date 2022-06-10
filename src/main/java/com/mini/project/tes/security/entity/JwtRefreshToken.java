package com.mini.project.tes.security.entity;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

public class JwtRefreshToken {
    private String token;
    private String accessToken;
    @NotNull
    private SamUserEntity user;

    private Instant expirationDateTime;
    private Date createdDate;
    private String loginIp;
    private String workgroup;

    public JwtRefreshToken() {
    }

    public JwtRefreshToken(String token) {
        this.token = token;
    }

    public JwtRefreshToken(String token, SamUserEntity user, Instant expirationDateTime) {
        this.token = token;
        this.user = user;
        this.expirationDateTime = expirationDateTime;
    }
    
    public JwtRefreshToken(String token, String accessToken, SamUserEntity user, Instant expirationDateTime, Date createdDate) {
        this.token = token;
        this.accessToken = accessToken;
        this.createdDate = createdDate;
        this.user = user;
        this.expirationDateTime = expirationDateTime;
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

    public Instant getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(Instant expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

    public SamUserEntity getUser() {
        return user;
    }

    public void setUser(SamUserEntity user) {
        this.user = user;
    }
}
