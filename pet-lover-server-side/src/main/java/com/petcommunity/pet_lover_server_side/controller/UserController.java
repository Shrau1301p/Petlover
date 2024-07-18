package com.petcommunity.pet_lover_server_side.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.dto.UserDetailsImpI;
import com.petcommunity.pet_lover_server_side.dto.UserRequest;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;
import com.petcommunity.pet_lover_server_side.service.UserServices;
import com.petcommunity.pet_lover_server_side.service.jwt.JwtTokenService;

@RestController
public class UserController {
	
	private UserServices userServices;
	private UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	PasswordEncoder encoder;
	
	@Autowired
	JwtTokenService jwtTokenService;
	
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
	public ResponseEntity<ResponseMessage<User>> getUserData(@RequestBody UserRequest user) {
		//userServices.storeUserDetails(user);
		//userRepository.save(user);
		//return ResponseEntity.ok("User registered successfully");
		ResponseMessage<User> responseMessage = new ResponseMessage<>();
		try {
			String password = user.getPassword();
			String encodePasswordString = encoder.encode(password);
			String usernameString = user.getUsername();
			User newUser = new User(usernameString,encodePasswordString);
			userRepository.save(newUser);
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
	
	@PostMapping(path="/login")
	public ResponseEntity<ResponseMessage<String>> login(@RequestBody UserRequest user){
		
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		
		try {
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			
			System.out.println(authentication);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenService.generateToken(authentication);
			
			responseMessage.setMessage("User Logged in Successfully");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        responseMessage.setData(jwt);
	        
	        return ResponseEntity.ok(responseMessage);
		
		} catch (Exception e) {
	    	
			responseMessage.setError("An error occurred");
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	    }
	}
}
