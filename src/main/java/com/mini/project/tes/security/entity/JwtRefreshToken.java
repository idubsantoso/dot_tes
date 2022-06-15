package com.mini.project.tes.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
@Data
@AllArgsConstructor
public class JwtRefreshToken {
    private String token;
    private String accessToken;
    @NotNull
    private SamUserEntity user;

    private Instant expirationDateTime;
    private Date createdDate;
    private String loginIp;
    private String workgroup;

}
