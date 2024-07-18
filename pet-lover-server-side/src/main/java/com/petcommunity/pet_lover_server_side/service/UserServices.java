package com.petcommunity.pet_lover_server_side.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	public void storeUserDetails(User user) {
		System.out.println(user);
		userRepository.save(user);
	}
}
