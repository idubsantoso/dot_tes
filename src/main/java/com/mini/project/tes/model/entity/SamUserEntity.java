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
public class SamUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="ACTIVE_STATUS", nullable=false, length=20)
	private String activeStatus;
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

	@Column(name="USER_ID",nullable=false, length=50)
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogIn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogOut;

	private int LoginAttempt;


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

	public int getLoginAttempt() {
		return LoginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		LoginAttempt = loginAttempt;
	}
}