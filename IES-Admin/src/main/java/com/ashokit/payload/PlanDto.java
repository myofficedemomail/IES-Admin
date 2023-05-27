package com.ashokit.payload;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PlanDto {
	private Integer planId;
	@NotEmpty(message = "Plan Name Can Not Be Empty")
	@Size(min = 4, max = 20, message = "Plan Should Be Between 4 To 20 Characters")
	private String planName;
	//@NotEmpty(message = "Plan Start Date Can Not Be Empty")
	private LocalDate planStartDate;
	//@NotEmpty(message = "Plan End Date Can Not Be Empty")
	private LocalDate planEndDate;
	private String planStatus;
}
