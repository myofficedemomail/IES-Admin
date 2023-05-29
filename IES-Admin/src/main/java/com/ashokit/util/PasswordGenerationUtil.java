package com.ashokit.util;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerationUtil {
	String password = null;

	public String generatePassword() {
		/*
		 * password=signUpForm.getMobileNo().substring(5,
		 * 9)+""+signUpForm.getUserName().substring(0, 5); return password;
		 */
		RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(65, 122).build();
		return pwdGenerator.generate(6).toString();
	}
}
