package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "IES_PLANS")
public class PlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAN_ID")
	private Integer planId;
	@Column(name = "PLAN_NAME")
	private String planName;
	@Column(name = "PLAN_START_DATE")
	private Date planStartDate;
	@Column(name = "PLAN_END_DATE")
	private Date planEndDate;
	@Column(name = "PLAN_STATUS")
	private String planStatus;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;
	@Column(name = "CREATED_DATE")
	private String createdDate;
}
