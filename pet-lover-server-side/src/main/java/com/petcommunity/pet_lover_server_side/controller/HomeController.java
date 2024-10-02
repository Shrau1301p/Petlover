package com.petcommunity.pet_lover_server_side.controller;


import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petcommunity.pet_lover_server_side.dto.ProfileDetails;
import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.service.UserServices;

@RestController
@RequestMapping("/home")
public class HomeController {

	private  UserServices userServices;
	
	public HomeController(UserServices userServices) {
		super();
		this.userServices = userServices;
	}


	@GetMapping("/me")
	private ResponseEntity<ResponseMessage<User>> authenticatedUser(){
		ResponseMessage<User> responseMessage = new ResponseMessage<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        User currentUser = (User) authentication.getPrincipal();
	        responseMessage.setData(currentUser);
//	        responseMessage.setMessage("Authentication Successfull");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	@PostMapping("/uploadImage")
	private ResponseEntity<ResponseMessage<String>> uploadImage(@RequestBody File file){
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File newFile = new File(classLoader.getResource(".").getFile() +"/static/avatra"+ file.getAbsolutePath());
			if (newFile.createNewFile()) {
			    System.out.println("File is created!");
			    responseMessage.setData(null);
			    responseMessage.setMessage("Image is updated");
			    responseMessage.setStatusCode(HttpStatus.OK.value());
			    return ResponseEntity.ok(responseMessage);
			} else {
			    System.out.println("File already exists.");
			    responseMessage.setData(null);
			    responseMessage.setMessage("Image is not updated");
			    responseMessage.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
			    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
			}
		}catch (Exception e) {
			// TODO: handle exception
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	
	@PutMapping("/add-profile")
	private ResponseEntity<ResponseMessage<User>> insertProfile(@RequestBody ProfileDetails profile){
		ResponseMessage<User> responseMessage = new ResponseMessage<>();
		try {
			User userProfile = userServices.addProfile(profile);
			responseMessage.setData(userProfile);
			responseMessage.setMessage("Profile Created Successfully");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
		}catch (Exception e) {
			// TODO: handle exception
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
}
