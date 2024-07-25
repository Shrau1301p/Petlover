package com.petcommunity.pet_lover_server_side.service;

import java.util.ArrayList;
import java.util.List;

import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;

public class HomeServices {
	
	private UserRepository userRepository;

	public HomeServices(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> allUsers(){
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
}
