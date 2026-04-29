package com.learn.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.UserDto;
import com.learn.ecommerce.entities.Role;
import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.enumes.AppRole;
import com.learn.ecommerce.repositories.RoleRepository;
import com.learn.ecommerce.repositories.UserRepository;
import com.learn.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

    UserServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		User user = modelMapper.map(userDto, User.class);
		
		Role role = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseThrow(()->new RuntimeException("Role not found"));
		
		user.setRole(role);
		user.setStatus(true);
		
		User savedUser = userRepository.save(user);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	public boolean checkEmailExists(String email)
	{
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserDto getUserById(String id) {
		
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list = userRepository.findAll();
		return list.stream().map(u->modelMapper.map(u, UserDto.class)).toList();
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		user.setFirstName(userDto.getFirstName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		User savedUser = userRepository.save(user);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public void activateUser(String id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		user.setStatus(true);
		userRepository.save(user);
		
	}

	@Override
	public void deactivateUser(String id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		user.setStatus(false);
		userRepository.save(user);
	}
	
	@Override
	public void deleteUser(String id)
	{
		userRepository.deleteById(id);
	}
	
}
