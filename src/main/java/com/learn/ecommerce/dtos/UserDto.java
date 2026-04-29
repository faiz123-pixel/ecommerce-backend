package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private Role role;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean status; 
	private List<Orders> orders; 
	

}
