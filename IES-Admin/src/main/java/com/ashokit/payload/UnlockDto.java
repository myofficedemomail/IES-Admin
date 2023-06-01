package com.ashokit.payload;

import lombok.Data;

@Data
public class UnlockDto {
	private String userEmail;
	private String temporaryPwd;
	private String userPwd;
	private String userConfirmPwd;
}
