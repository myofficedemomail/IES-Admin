package com.ashokit.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CaseWorkerDto {
	private Integer caseWorkerId;
	@NotEmpty(message = "Case Worker Name Can Not Be Empty")
	@Size(min = 4, max = 25, message = "Case Worker Name Should Be Between 4 To 25 Characters")
	private String caseWorkerName;
	@Email(message = "Must Be A Well-Formed Email Address")
	@NotEmpty(message = "Mail Id Can Not Be Empty")
	private String emailId;
	@NotEmpty
	@Pattern(regexp = "(^$|[6-9]{1}[0-9]{9})", message = "Mobile Number Should Be 10 Digits And Must Be Start With 6 to 9")
	private String mobileNumber;
	@Pattern(regexp = "(?:m|M|f|F)$", message = "Gender Should Be M/F Format")
	private String gender;
	@NotEmpty(message = "Dob Can Not Be Empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "Invalid Date Format. Format Should Be yyyy-mm-dd")
	private String dob;
	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{12})", message = "SSN Number Should Be 12 Digits")
	private String ssnNo;
}
