package com.petcommunity.pet_lover_server_side.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
