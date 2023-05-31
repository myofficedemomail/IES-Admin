package com.ashokit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class TestRunner implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("*************************"+pwdEncoder.encode("123"));

	}

}
