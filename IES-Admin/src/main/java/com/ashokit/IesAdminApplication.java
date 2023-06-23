package com.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class IesAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(IesAdminApplication.class, args);
		System.out.println("*******************************************");
		System.out.println("*										  ");
		System.out.println("* 										  ");
		System.out.println("*  Username::myofficedemomail@gmail.com	  ");
		System.out.println("*  Password::123           				  ");
		System.out.println("* 										  ");
		System.out.println("* 										  ");
		System.out.println("*******************************************");
	}
}
