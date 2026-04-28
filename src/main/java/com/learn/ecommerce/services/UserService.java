package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.UserDto;

public interface UserService {
	
	UserDto registerUser(UserDto userDto);
	boolean checkEmailExists(String email);
	UserDto getUserById(String id); 
	List<UserDto> getAllUser();
	
}
