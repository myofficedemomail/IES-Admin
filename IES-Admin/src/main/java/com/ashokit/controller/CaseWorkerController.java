package com.ashokit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.payload.UserDto;
import com.ashokit.service.UserService;

@RestController
@RequestMapping(value = "/admin/caseworker/")
public class CaseWorkerController {
	@Autowired
	private UserService userService;

	@PostMapping("createAccount")
	public ResponseEntity<UserDto> createAccountForCaseWorker(@Valid @RequestBody UserDto UserDto) {
		UserDto createdCaseWorkerAccount = userService.createUser(UserDto);
		return new ResponseEntity<>(createdCaseWorkerAccount, HttpStatus.CREATED);
	}

	@GetMapping("allAccounts")
	public ResponseEntity<List<UserDto>> getAllCaseWorkersAccount() {
		List<UserDto> allCaseWorkers = userService.getAllUsers();
		return new ResponseEntity<>(allCaseWorkers, HttpStatus.OK);
	}

	@GetMapping("getAccount/{accountId}")
	public ResponseEntity<UserDto> getPlanById(@PathVariable(name = "accountId") Integer accountId) {
		UserDto UserDto = userService.getUserById(accountId);
		return new ResponseEntity<UserDto>(UserDto, HttpStatus.OK);
	}

	@PutMapping("updateAccount/{accountId}")
	public ResponseEntity<UserDto> updatePlan(@PathVariable Integer accountId, @Valid @RequestBody UserDto UserDto) {
		UserDto updatedCaseWorker = userService.updateUser(UserDto, accountId);
		return new ResponseEntity<>(updatedCaseWorker, HttpStatus.OK);
	}
}
