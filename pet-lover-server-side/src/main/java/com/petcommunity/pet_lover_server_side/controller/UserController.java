package com.petcommunity.pet_lover_server_side.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;
import com.petcommunity.pet_lover_server_side.service.UserServices;

@RestController
public class UserController {
	
	private UserServices userServices;
	private UserRepository userRepository;
	
	public UserController(UserServices userServices,UserRepository userRepository) {
		super();
		this.userServices = userServices;
		this.userRepository = userRepository;
	}
	
	
		//-------------------------------
		// Add new User Details 
		///register
		//-------------------------------
	@PostMapping(path = "/register")
	public ResponseEntity<ResponseMessage<User>> getUserData(@RequestBody User user) {
//		userServices.storeUserDetails(user);
//		userRepository.save(user);
//		return ResponseEntity.ok("User registered successfully");
		ResponseMessage<User> responseMessage = new ResponseMessage<>();
		try {
			userRepository.save(user);
	        responseMessage.setMessage("User registered successfully");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
	    } catch (DataIntegrityViolationException e) {
	    	System.out.println(e);
	    	responseMessage.setError("User already exists");
	        responseMessage.setStatusCode(HttpStatus.CONFLICT.value());
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMessage);
	    } catch (Exception e) {
	    	responseMessage.setError("An error occurred");
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	    }
	} 
}
