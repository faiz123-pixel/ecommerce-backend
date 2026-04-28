package com.learn.ecommerce.dtos;

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
	private String password;
	private Role role;
	private List<Orders> orders; 
	

}
