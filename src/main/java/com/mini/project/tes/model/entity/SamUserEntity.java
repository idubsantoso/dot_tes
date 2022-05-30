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
	@Column(nullable=false, length=50)
	private String password;
	//@Column(name="PASSWORD_TEMP", length=50)
	//private String passwordTemp;
	@Column(name="username",nullable=false, length=50)
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogIn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogOut;

	private Integer LoginAttempt;


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
		return LoginAttempt;
	}

	public void setLoginAttempt(Integer loginAttempt) {
		LoginAttempt = loginAttempt;
	}
}