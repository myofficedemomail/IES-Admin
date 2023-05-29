package com.ashokit.payload;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PlanDto {
	private Integer planId;
	@NotEmpty(message = "Plan Name Can Not Be Empty")
	@Size(min = 4, max = 20, message = "Plan Should Be Between 4 To 20 Characters")
	private String planName;
	@NotEmpty(message = "Plan Start Date Can Not Be Empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message="Invalid Date Format. Format Should Be yyyy-mm-dd")
	private String planStartDate;
	@NotEmpty(message = "Plan End Date Can Not Be Empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message="Invalid Date Format. Format Should Be yyyy-mm-dd")
	private String planEndDate;
	private String planStatus;
}
