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

import com.ashokit.payload.ApiResponse;
import com.ashokit.payload.PlanDto;
import com.ashokit.service.PlanService;

@RestController
@RequestMapping("/admin/plan/")
public class PlanController {
	@Autowired
	private PlanService planService;

	@PostMapping("createPlan")
	public ResponseEntity<PlanDto> createPlan(@Valid @RequestBody PlanDto planDto) {
		PlanDto createdPlan = planService.createPlan(planDto);
		return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
	}

	@GetMapping("allPlans")
	public ResponseEntity<List<PlanDto>> getAllPlans() {
		List<PlanDto> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("getPlan/{planId}")
	public ResponseEntity<PlanDto> getPlanById(@PathVariable(name = "planId") Integer planId) {
		PlanDto planDto = planService.getPlanById(planId);
		return new ResponseEntity<PlanDto>(planDto, HttpStatus.OK);
	}

	@PutMapping("updatePlan/{planId}")
	public ResponseEntity<PlanDto> updatePlan(@PathVariable Integer planId, @Valid @RequestBody PlanDto planDto) {
		PlanDto updatedPlan = planService.updatePlan(planDto, planId);
		return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
	}

	@PutMapping("switchPlan/{planId}")
	public ResponseEntity<ApiResponse> switchPlan(@PathVariable Integer planId){
		boolean flag = planService.switchPlan(planId);
		ApiResponse apiResponse=new ApiResponse();
		if(flag) {
			apiResponse.setMessage("Successfully Switched");
			apiResponse.setSuccess(true);
		}else {
			apiResponse.setMessage("Failed To Switched");
			apiResponse.setSuccess(false);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
