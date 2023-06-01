package com.ashokit.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "IES_USER")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "FULL_NAME")
	private String fullName;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_PHNO")
	private Long userPhno;
	@Column(name = "USER_GENDER")
	private String userGender;
	@Column(name = "USER_DOB")
	private Date userDob;
	@Column(name = "USER_SSN")
	private Long userSsn;
	@Column(name = "USER_PWD")
	private String userPwd;
	@Column(name = "ACTIVE_SW")
	private String activeSw;
	@Column(name = "ACC_STATUS")
	private String accStatus;
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<UserRoles> listUserRoles;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
}
