package com.learn.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.ecommerce.enumes.AppRole;

import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private AppRole  roleName;
	
	@OneToMany(mappedBy = "role")
	@JsonIgnore
	private List<User> users;

}
