package com.ashokit.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ashokit.entity.UserEntity;
import com.ashokit.entity.UserRoles;
import com.ashokit.repo.UserRepo;
//@Component
public class TestRunner implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	@Autowired
	private UserRepo userRepo;
	@Override
	public void run(String... args) throws Exception {
		UserEntity user=new UserEntity();
		user.setUserEmail("myofficedemomail@gmail.com");
		user.setUserPwd(pwdEncoder.encode("123"));
		UserRoles role=new UserRoles();
		role.setRoleName("Admin");
		user.setListUserRoles(Arrays.asList(role));
		userRepo.save(user);

	}

}
