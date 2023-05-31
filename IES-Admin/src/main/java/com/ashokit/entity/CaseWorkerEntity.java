package com.ashokit.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CaseWorkerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer caseWorkerId;
	private String caseWorkerName;
	private String emailId;
	private Long mobileNumber;
	private String gender;
	private Date dob;
	private Long ssnNo;
	private Date dtTime;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
}
