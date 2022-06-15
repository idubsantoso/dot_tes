package com.mini.project.tes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
public class User {
    private Long id;

    @NotNull
    @Length(max = 50)
    private String password;
    @NotNull
    private Boolean passwordCanExpire;

    @NotNull
    @Length(max = 50)
    private String username;
    //private String usernameTemp;

    private Date lastLogIn;
    private Date lastLogOut;
    private Integer loginAttempt;

}
