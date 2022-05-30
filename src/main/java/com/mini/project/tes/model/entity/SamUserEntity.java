package com.mini.project.tes.model.entity;

import lombok.*;
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
@Data
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

}