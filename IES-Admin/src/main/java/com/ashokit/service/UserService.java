package com.ashokit.service;

import java.util.List;

import com.ashokit.exception.ResourceNotFoundException;
import com.ashokit.payload.UnlockDto;
import com.ashokit.payload.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	List<UserDto> getAllUsers();

	UserDto getUserById(Integer userId) throws ResourceNotFoundException;

	UserDto updateUser(UserDto userDto, Integer userId) throws ResourceNotFoundException;

	String unlockUser(UnlockDto unlockDto) throws ResourceNotFoundException;
}
