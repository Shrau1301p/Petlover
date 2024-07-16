package com.petcommunity.pet_lover_server_side.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.service.UserServices;

@RestController
public class UserController {
	
	private UserServices userServices;
	
	
	public UserController(UserServices userServices) {
		super();
		this.userServices = userServices;
	}

	@PostMapping(path = "/register")
	public void getUserData(@RequestBody User user) {
		userServices.storeUserDetails(user);
	} 
}
