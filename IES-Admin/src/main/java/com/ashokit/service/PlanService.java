package com.ashokit.service;

import java.util.List;

import com.ashokit.exception.ResourceNotFoundException;
import com.ashokit.payload.PlanDto;

public interface PlanService {

	PlanDto createPlan(PlanDto planDto);

	List<PlanDto> getAllPlans();

	PlanDto getPlanById(Integer planId) throws ResourceNotFoundException;

	PlanDto updatePlan(PlanDto planDto, Integer planId) throws ResourceNotFoundException;
}
