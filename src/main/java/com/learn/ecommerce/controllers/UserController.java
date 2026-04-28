package com.learn.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dtos.UserDto;
import com.learn.ecommerce.services.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	
	@Autowired
	private UserService userService;


	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody UserDto userDto)
	{
		UserDto savedUser = userService.registerUser(userDto);
		
		return new ResponseEntity<UserDto>(savedUser,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/check-email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email)
	{
		
		return ResponseEntity.ok(userService.checkEmailExists(email));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable String id)
	{
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	

}
