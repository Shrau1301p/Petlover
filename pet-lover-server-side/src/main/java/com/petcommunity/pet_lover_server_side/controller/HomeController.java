package com.petcommunity.pet_lover_server_side.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.model.User;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/me")
	private ResponseEntity<ResponseMessage<User>> authenticatedUser(){
		ResponseMessage<User> responseMessage = new ResponseMessage<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        User currentUser = (User) authentication.getPrincipal();
	        responseMessage.setData(currentUser);
	        responseMessage.setMessage("Authentication Successfull");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
}
