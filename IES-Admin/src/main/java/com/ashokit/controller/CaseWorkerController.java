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

import com.ashokit.payload.CaseWorkerDto;
import com.ashokit.service.CaseWorkerService;

@RestController
@RequestMapping(value = "/admin/caseworker/")
public class CaseWorkerController {
	@Autowired
	private CaseWorkerService caseWorkerService;

	@PostMapping("createAccount")
	public ResponseEntity<CaseWorkerDto> createAccountForCaseWorker(@Valid @RequestBody CaseWorkerDto caseWorkerDto) {
		CaseWorkerDto createdCaseWorkerAccount = caseWorkerService.createCaseWorker(caseWorkerDto);
		return new ResponseEntity<>(createdCaseWorkerAccount, HttpStatus.CREATED);
	}

	@GetMapping("allAccounts")
	public ResponseEntity<List<CaseWorkerDto>> getAllCaseWorkersAccount() {
		List<CaseWorkerDto> allCaseWorkers = caseWorkerService.getAllCaseWorkers();
		return new ResponseEntity<>(allCaseWorkers, HttpStatus.OK);
	}

	@GetMapping("getAccount/{accountId}")
	public ResponseEntity<CaseWorkerDto> getPlanById(@PathVariable(name = "accountId") Integer accountId) {
		CaseWorkerDto caseWorkerDto = caseWorkerService.getCaseWorkerById(accountId);
		return new ResponseEntity<CaseWorkerDto>(caseWorkerDto, HttpStatus.OK);
	}

	@PutMapping("updateAccount/{accountId}")
	public ResponseEntity<CaseWorkerDto> updatePlan(@PathVariable Integer accountId,
			@Valid @RequestBody CaseWorkerDto caseWorkerDto) {
		CaseWorkerDto updatedCaseWorker = caseWorkerService.updateCaseWorker(caseWorkerDto, accountId);
		return new ResponseEntity<>(updatedCaseWorker, HttpStatus.OK);
	}
}
