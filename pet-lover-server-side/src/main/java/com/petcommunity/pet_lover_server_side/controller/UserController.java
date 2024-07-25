package com.petcommunity.pet_lover_server_side.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.dto.UserRequest;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.service.JwtAuthenticationService;
import com.petcommunity.pet_lover_server_side.service.UserServices;

@RestController
@RequestMapping("/auth")
public class UserController {
	private JwtAuthenticationService jwtAuthenticationService;
	private  UserServices userServices;
	
	public UserController(JwtAuthenticationService jwtAuthenticationService, UserServices userServices) {
		super();
		this.jwtAuthenticationService = jwtAuthenticationService;
		this.userServices = userServices;
	}
	
	@PostMapping("/register")
    public ResponseEntity<ResponseMessage<String>> register(@RequestBody UserRequest registerUserDto) {
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		try {
        	User registeredUser = userServices.signup(registerUserDto);
			responseMessage.setMessage("User Registered Successfully");
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

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<String>> authenticate(@RequestBody UserRequest loginUserDto) {
        User authenticatedUser = userServices.authenticate(loginUserDto);
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        try {
        	String jwtToken = jwtAuthenticationService.generateToken(authenticatedUser);
			responseMessage.setMessage("User Logged in Successfully");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        responseMessage.setData(jwtToken);
	        
	        return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			responseMessage.setError("An error occurred");
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}

    }
	
}
