package com.mini.project.tes.model.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the sam_user database table.
 * 
 */
@Entity
@Table(name="sam_user")
public class SamUserEntity extends AuditableNotAuto<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="ACTIVE_STATUS", nullable=false, length=20)
	private String activeStatus;
	@Email
	private String email;
	@Column(name="CREATED_IP", length=15)
	private String createdIp;
	@Column(name="EDIT_REASON", length=100)
	private String editReason;
	@Column(name="FIRST_NAME", length=25)
	private String firstName;
	//@Column(name="FIRST_NAME_TEMP", length=25)
	//private String firstNameTemp;
	@Column(name="LAST_NAME", length=25)
	private String lastName;
	//@Column(name="LAST_NAME_TEMP", length=25)
	//private String lastNameTemp;
	@Column(nullable=false, length=50)
	private String password;
	@Column(name="PASSWORD_CAN_EXPIRE", nullable=false)
	@Type(type="yes_no")
	private Boolean passwordCanExpire;
	//@Column(name="PASSWORD_TEMP", length=50)
	//private String passwordTemp;
	@Column(name="UPDATE_IP", length=15)
	private String updateIp;
	@Column(name="USER_DEPARTMENT", nullable=true, length=50)
	private String userDepartment;
	//@Column(name="USER_DEPARTMENT_TEMP", length=50)
	//private String userDepartmentTemp;
	@Column(name="USER_ID",nullable=false, length=50)
	private String username;
	//@Column(name="USERNAME_TEMP", length=50)
	//private String usernameTemp;
	@Column(name="super_user",nullable=false, length=1)
	private String superUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogIn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogOut;

	private int LoginAttempt;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "sam_user_roles",
    	joinColumns = @JoinColumn(name = "user_id"),
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<SamRoleEntity> roles;

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

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
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

	public String getSuperUser() {
		return superUser;
	}

	public void setSuperUser(String superUser) {
		this.superUser = superUser;
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

	public Set<SamRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<SamRoleEntity> roles) {
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

	public int getLoginAttempt() {
		return LoginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		LoginAttempt = loginAttempt;
	}
}