package com.mini.project.tes.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;

}
