package com.mini.project.tes.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class User {
    private Long id;

    @NotNull
    @Length(max = 20)
    private String activeStatus;

    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;
    @Length(max = 100)
    private String editReason;

    @NotNull
    @Length(max = 25)
    private String firstName;
    @NotNull
    @Length(max = 25)
    private String lastName;
    //private String lastNameTemp;
    @NotNull
    @Length(max = 50)
    private String password;
    @NotNull
    private Boolean passwordCanExpire;
    //private String passwordTemp;
    @Length(max = 15)
    private String createdIp;
    private String updateIp;
    @NotNull
    @Length(max = 50)
    private String userDepartment;
    //private String userDepartmentTemp;
    @NotNull
    @Length(max = 50)
    private String username;
    //private String usernameTemp;

    private Date lastLogIn;
    private Date lastLogOut;
    private Integer loginAttempt;

    private String superUser;

    private Set<Role> roles;

    public User() {
    }

    public User(Long id, String activeStatus, @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") String email, String editReason, String firstName, String lastName, String password, Boolean passwordCanExpire, String createdIp, String updateIp, String userDepartment, String username, Date lastLogIn, Date lastLogOut, Integer loginAttempt, Set<Role> roles, String superUser) {
        this.id = id;
        this.activeStatus = activeStatus;
        this.email = email;
        this.editReason = editReason;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordCanExpire = passwordCanExpire;
        this.createdIp = createdIp;
        this.updateIp = updateIp;
        this.userDepartment = userDepartment;
        this.username = username;
        this.lastLogIn = lastLogIn;
        this.lastLogOut = lastLogOut;
        this.loginAttempt = loginAttempt;
        this.roles = roles;
        this.superUser = superUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEditReason() {
        return editReason;
    }

    public void setEditReason(String editReason) {
        this.editReason = editReason;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /*
    public String getFirstNameTemp() {
        return firstNameTemp;
    }

    public void setFirstNameTemp(String firstNameTemp) {
        this.firstNameTemp = firstNameTemp;
    }

     */

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /*
    public String getLastNameTemp() {
        return lastNameTemp;
    }

    public void setLastNameTemp(String lastNameTemp) {
        this.lastNameTemp = lastNameTemp;
    }

     */

    public String getSuperUser() {
        return superUser;
    }

    public void setSuperUser(String superUser) {
        this.superUser = superUser;
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
    /*
    public String getPasswordTemp() {
        return passwordTemp;
    }

    public void setPasswordTemp(String passwordTemp) {
        this.passwordTemp = passwordTemp;
    }

     */

    public String getCreatedIp() {
        return createdIp;
    }

    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }
    /*
    public String getUserDepartmentTemp() {
        return userDepartmentTemp;
    }

    public void setUserDepartmentTemp(String userDepartmentTemp) {
        this.userDepartmentTemp = userDepartmentTemp;
    }

     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    /*
    public String getUsernameTemp() {
        return usernameTemp;
    }

    public void setUsernameTemp(String usernameTemp) {
        this.usernameTemp = usernameTemp;
    }

     */

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
