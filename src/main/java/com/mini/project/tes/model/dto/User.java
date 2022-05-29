package com.mini.project.tes.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

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


    public User() {
    }

    public User(Long id,String password, Boolean passwordCanExpire,String username, Date lastLogIn, Date lastLogOut, Integer loginAttempt) {
        this.id = id;
        this.password = password;
        this.passwordCanExpire = passwordCanExpire;
        this.username = username;
        this.lastLogIn = lastLogIn;
        this.lastLogOut = lastLogOut;
        this.loginAttempt = loginAttempt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordCanExpire() {
        return passwordCanExpire;
    }

    public void setPasswordCanExpire(Boolean passwordCanExpire) {
        this.passwordCanExpire = passwordCanExpire;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(Date lastLogIn) {
        this.lastLogIn = lastLogIn;
    }

    public Date getLastLogOut() {
        return lastLogOut;
    }

    public void setLastLogOut(Date lastLogOut) {
        this.lastLogOut = lastLogOut;
    }

    public Integer getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;
    }
}
