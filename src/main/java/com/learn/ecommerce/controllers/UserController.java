package com.learn.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dtos.UserDto;
import com.learn.ecommerce.security.UserDetailsImpl;
import com.learn.ecommerce.services.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserDetailsImpl userDetailsImpl;

	
	@Autowired
	private UserService userService;


    UserController(UserDetailsImpl userDetailsImpl) {
        this.userDetailsImpl = userDetailsImpl;
    }


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
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto)
	{
		return ResponseEntity.ok(userService.updateUser(id, userDto));
	}
	
	@PutMapping("/activate/{id}")
	public ResponseEntity<String> activateUser(@PathVariable String id)
	{
		userService.activateUser(id);
		return ResponseEntity.ok("Status update successfully");
	}
	
	@PutMapping("/deactivate/{id}")
	public ResponseEntity<String> deactivateUser(@PathVariable String id)
	{
		userService.deactivateUser(id);
		return ResponseEntity.ok("Status update successfully");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id)
	{
		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted Successfully");
	}
	

}
