package com.ashokit.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashokit.entity.UserEntity;
import com.ashokit.exception.ResourceNotFoundException;
import com.ashokit.payload.UnlockDto;
import com.ashokit.payload.UserDto;
import com.ashokit.repo.UserRepo;
import com.ashokit.service.UserService;
import com.ashokit.util.AppConstants;
import com.ashokit.util.MailUtils;
import com.ashokit.util.PasswordGenerationUtil;

@Service
public class CaseWorkerServiceImpl implements UserService , UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SimpleDateFormat dtFormat;
	@Autowired
	private MailUtils mailUtil;
	@Autowired
	private PasswordGenerationUtil passwordGenerationUtil;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;


	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		String password=passwordGenerationUtil.generatePassword();
		userEntity.setUserId(null);
		userEntity.setUserPwd(password);
		userEntity.setAccStatus(AppConstants.LOCKED);
		userEntity.setActiveSw(AppConstants.ACTIVE);
		userEntity.setCreatedBy(AppConstants.ADMIN);
		userEntity.setCreatedDate(new Date());
		userEntity = userRepo.save(userEntity);
		userDto = modelMapper.map(userEntity, UserDto.class);
		userDto.setUserDob(dtFormat.format(userEntity.getUserDob()));
		
		
		String subject="Unlock Your Account | IES Admin";
		StringBuffer body=new StringBuffer();
		body.append("<h1>Hello "+userDto.getFullName()+" ,</h1>");
		body.append("Please find below temporary password to unlock your account.");
		body.append("<br><a href='http://localhost:9090/admin/caseworker/unlockAccount?email="+userDto.getUserEmail()+"'>Click here to activate the account</a>");
		body.append("<h2 style='color:red;'>Temporary Password::"+password+"</h2>");
		body.append("<br><br>");
		body.append("<b>Regards,<br>IES Admin | Cheers</b>");
		mailUtil.sendEmail(subject, body.toString(), userDto.getUserEmail());
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> allUser = userRepo.findAll();
		List<UserDto> allUserDto = null;
		/*
		 * PlanDto planDto=null; for(PlanEntity entity:allPlans) {
		 * planDto=modelMapper.map(entity,PlanDto.class); allPlanDtos.add(planDto); }
		 */
		allUserDto = allUser.stream().map((caseWorker) -> modelMapper.map(caseWorker, UserDto.class))
				.collect(Collectors.toList());
		return allUserDto;
	}

	@Override
	public UserDto getUserById(Integer userId) throws ResourceNotFoundException {
		UserEntity UserEntity = userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("Case Worker", "Case Worker Id", userId.toString()));
		UserDto userDto = modelMapper.map(UserEntity, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId)
			throws ResourceNotFoundException {
		UserEntity userEntity = userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("Case Worker", "Case Worker Id", userId.toString()));
		UserEntity userEntity2 = modelMapper.map(userDto, UserEntity.class);
		userEntity2.setUserId(userId);
		userEntity2 = userRepo.save(userEntity2);
		userDto = modelMapper.map(userEntity2, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByUserEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(
					new StringBuffer().append("User name ").append(username).append(" not found!").toString());

		List<GrantedAuthority> authorities = userEntity.getListUserRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(username, userEntity.getUserPwd(), authorities);
	}

	@Override
	public String unlockUser(UnlockDto unlockDto) throws ResourceNotFoundException {
		UserEntity userEntity = userRepo.findByUserEmail(unlockDto.getUserEmail());
		String msg="";
		if(userEntity!=null) {
			if(unlockDto.getUserPwd().equals(unlockDto.getUserConfirmPwd())) {
				if(userEntity.getUserPwd().equals(unlockDto.getTemporaryPwd())) {
					if(userEntity.getAccStatus().equals(AppConstants.LOCKED)) {
						userEntity.setUserPwd(pwdEncoder.encode(unlockDto.getUserPwd()));
						userEntity.setAccStatus(AppConstants.UNLOCKED);
						userEntity.setUpdatedBy(AppConstants.CASEWORKER);
						userEntity.setUpdatedDate(new Date());
						userRepo.save(userEntity);
						msg="Account Successfully Unlocked";
						return msg;
					}else {
						msg="Your Account Is Already Unlocked";
						return msg;
					}
				}else {
					msg="Incorrect Temporary Password";
					return msg;
				}
			}else {
				msg="Password And Confirm Password Not Matched";
				return msg;
			}
		}else {
			throw new ResourceNotFoundException("Case Worker", "Case Worker Mail Id", unlockDto.getUserEmail());
		}
	}

}
