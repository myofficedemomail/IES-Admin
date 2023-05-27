package com.ashokit.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.PlanEntity;
import com.ashokit.exception.ResourceNotFoundException;
import com.ashokit.payload.PlanDto;
import com.ashokit.repo.PlanRepo;
import com.ashokit.service.PlanService;
@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PlanDto createPlan(PlanDto planDto) {
		PlanEntity planEntity = modelMapper.map(planDto, PlanEntity.class);
		planEntity.setPlanStatus("A");
		planEntity=planRepo.save(planEntity);
		planDto=modelMapper.map(planEntity, PlanDto.class);
		return planDto;
	}

	@Override
	public List<PlanDto> getAllPlans() {
		List<PlanEntity> allPlans = planRepo.findAll();
		List<PlanDto> allPlanDtos=null;
		/*PlanDto planDto=null;
		for(PlanEntity entity:allPlans) {
			planDto=modelMapper.map(entity,PlanDto.class);
			allPlanDtos.add(planDto);
		}*/
		allPlanDtos=allPlans.stream().map((plan)->modelMapper.map(plan, PlanDto.class)).collect(Collectors.toList());
		return allPlanDtos;
	}

	@Override
	public PlanDto getPlanById(Integer planId) throws ResourceNotFoundException {
		PlanEntity planEntity = planRepo.findById(planId).orElseThrow(()->new ResourceNotFoundException("Plan", "Plan Id", planId.toString()));
		PlanDto planDto = modelMapper.map(planEntity, PlanDto.class);
		return planDto;
	}

	@Override
	public PlanDto updatePlan(PlanDto planDto, Integer planId) throws ResourceNotFoundException {
		PlanEntity planEntity = planRepo.findById(planId).orElseThrow(()->new ResourceNotFoundException("Plan", "Plan Id", planId.toString()));
		PlanEntity planEntity2 = modelMapper.map(planDto, PlanEntity.class);
		planEntity2.setPlanId(planId);
		planEntity2=planRepo.save(planEntity2);
		planDto=modelMapper.map(planEntity2, PlanDto.class);
		return planDto;
	}

}
