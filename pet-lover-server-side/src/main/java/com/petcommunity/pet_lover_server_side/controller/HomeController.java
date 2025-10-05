package com.petcommunity.pet_lover_server_side.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.petcommunity.pet_lover_server_side.dto.ProfileDetails;
import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.model.Images;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.service.ImageServices;
import com.petcommunity.pet_lover_server_side.service.UserServices;

@RestController
@RequestMapping("/home")
public class HomeController {

	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
	private  UserServices userServices;
	private ImageServices imageServices;
	
	
	public HomeController(UserServices userServices, ImageServices imageServices) {
		super();
		this.userServices = userServices;
		this.imageServices = imageServices;
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
	private ResponseEntity<ResponseMessage<Images>> uploadImage(@RequestParam("file") MultipartFile file) {
	    ResponseMessage<Images> responseMessage = new ResponseMessage<>();
	    try {
//	        // Get the resource folder path
//	        ClassLoader classLoader = getClass().getClassLoader();
//	        File resourceFolder = new File(classLoader.getResource("").getFile());
//
//	        // Create a directory inside the resources folder (static/avatar)
//	        String targetDirectoryPath = resourceFolder.getAbsolutePath() + "/static/avatar";
//	        File targetDirectory = new File(targetDirectoryPath);
//	        if (!targetDirectory.exists()) {
//	            targetDirectory.mkdirs();  // Creates the directory if it doesn't exist
//	        }
//	        
//	        // Save the file in the target directory
//	        File newFile = new File(targetDirectory, file.getOriginalFilename());
//	        Files.copy(file.getInputStream(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//	        // Build the response
//	        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//	                .path("/static/profileImage/")
//	                .path(file.getOriginalFilename())
//	                .toUriString();
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        User currentUser = (User) authentication.getPrincipal();
	        Images img = imageServices.uploadImage(file,currentUser.getId());
	        responseMessage.setData(img);
	        responseMessage.setMessage("Image uploaded successfully");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
	    } catch (Exception e) {
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
