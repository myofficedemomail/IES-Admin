package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.CaseWorkerEntity;
import java.util.List;


public interface CaseWorkerRepo extends JpaRepository<CaseWorkerEntity, Integer> {
	CaseWorkerEntity findByEmailId(String emailId);
}
