package com.ashokit.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.CaseWorkerEntity;
import com.ashokit.entity.PlanEntity;
import com.ashokit.exception.ResourceNotFoundException;
import com.ashokit.payload.CaseWorkerDto;
import com.ashokit.payload.PlanDto;
import com.ashokit.repo.CaseWorkerRepo;
import com.ashokit.service.CaseWorkerService;
import com.ashokit.util.MailUtils;
import com.ashokit.util.PasswordGenerationUtil;

@Service
public class CaseWorkerServiceImpl implements CaseWorkerService {
	@Autowired
	private CaseWorkerRepo caseWorkerRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SimpleDateFormat dtFormat;
	@Autowired
	private MailUtils mailUtil;
	@Autowired
	private PasswordGenerationUtil passwordGenerationUtil;

	@Override
	public CaseWorkerDto createCaseWorker(CaseWorkerDto caseWorkerDto) {
		CaseWorkerEntity caseWorkerEntity = modelMapper.map(caseWorkerDto, CaseWorkerEntity.class);
		String password=passwordGenerationUtil.generatePassword();
		caseWorkerEntity.setCaseWorkerId(null);
		caseWorkerEntity.setDtTime(new Date());
		caseWorkerEntity.setPassword(password);
		caseWorkerEntity = caseWorkerRepo.save(caseWorkerEntity);
		caseWorkerDto = modelMapper.map(caseWorkerEntity, CaseWorkerDto.class);
		caseWorkerDto.setDob(dtFormat.format(caseWorkerEntity.getDob()));
		
		
		String subject="Unlock Your Account | IES Admin";
		StringBuffer body=new StringBuffer();
		body.append("<h1>Hello "+caseWorkerDto.getCaseWorkerName()+" ,</h1>");
		body.append("Please find below temporary password to unlock your account.");
		body.append("<br><a href='http://localhost:9090/admin/caseworker/unlockAccount?email="+caseWorkerDto.getEmailId()+"'>Click here to activate the account</a>");
		body.append("<h2 style='color:red;'>Temporary Password::"+password+"</h2>");
		body.append("<br><br>");
		body.append("<b>Regards,<br>IES Admin | Cheers</b>");
		mailUtil.sendEmail(subject, body.toString(), caseWorkerDto.getEmailId());
		
		return caseWorkerDto;
	}

	@Override
	public List<CaseWorkerDto> getAllCaseWorkers() {
		List<CaseWorkerEntity> allCaseWorker = caseWorkerRepo.findAll();
		List<CaseWorkerDto> allCaseWorkerDto = null;
		/*
		 * PlanDto planDto=null; for(PlanEntity entity:allPlans) {
		 * planDto=modelMapper.map(entity,PlanDto.class); allPlanDtos.add(planDto); }
		 */
		allCaseWorkerDto = allCaseWorker.stream().map((caseWorker) -> modelMapper.map(caseWorker, CaseWorkerDto.class))
				.collect(Collectors.toList());
		return allCaseWorkerDto;
	}

	@Override
	public CaseWorkerDto getCaseWorkerById(Integer caseWorkerId) throws ResourceNotFoundException {
		CaseWorkerEntity caseWorkerEntity = caseWorkerRepo.findById(caseWorkerId).orElseThrow(
				() -> new ResourceNotFoundException("Case Worker", "Case Worker Id", caseWorkerId.toString()));
		CaseWorkerDto caseWorkerDto = modelMapper.map(caseWorkerEntity, CaseWorkerDto.class);
		return caseWorkerDto;
	}

	@Override
	public CaseWorkerDto updateCaseWorker(CaseWorkerDto caseWorkerDto, Integer caseWorkerId)
			throws ResourceNotFoundException {
		CaseWorkerEntity caseWorkerEntity = caseWorkerRepo.findById(caseWorkerId).orElseThrow(
				() -> new ResourceNotFoundException("Case Worker", "Case Worker Id", caseWorkerId.toString()));
		CaseWorkerEntity caseWorkerEntity2 = modelMapper.map(caseWorkerDto, CaseWorkerEntity.class);
		caseWorkerEntity2.setCaseWorkerId(caseWorkerId);
		caseWorkerEntity2 = caseWorkerRepo.save(caseWorkerEntity2);
		caseWorkerDto = modelMapper.map(caseWorkerEntity2, CaseWorkerDto.class);
		return caseWorkerDto;
	}

}
