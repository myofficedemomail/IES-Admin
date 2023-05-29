package com.ashokit.service;

import java.util.List;

import com.ashokit.exception.ResourceNotFoundException;
import com.ashokit.payload.CaseWorkerDto;
import com.ashokit.payload.PlanDto;

public interface CaseWorkerService {

	CaseWorkerDto createCaseWorker(CaseWorkerDto caseWorkerDto);

	List<CaseWorkerDto> getAllCaseWorkers();

	CaseWorkerDto getCaseWorkerById(Integer caseWorkerId) throws ResourceNotFoundException;

	CaseWorkerDto updateCaseWorker(CaseWorkerDto caseWorkerDto, Integer planId) throws ResourceNotFoundException;
}
