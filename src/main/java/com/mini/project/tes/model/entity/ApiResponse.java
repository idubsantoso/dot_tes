package com.mini.project.tes.model.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Winner [Alpabit]
 *
 * Dec 4, 2019
 */
@Getter
@Setter
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
