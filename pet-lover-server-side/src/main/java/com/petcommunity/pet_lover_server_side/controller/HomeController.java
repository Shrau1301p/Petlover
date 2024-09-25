package com.petcommunity.pet_lover_server_side.controller;


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
import org.springframework.web.bind.annotation.RestController;

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
	        responseMessage.setMessage("Authentication Successfull");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	
	@PostMapping("/uploadImage")
	private ResponseEntity<ResponseMessage<String>> uploadImage(@RequestBody ProfileDetails profile){
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		try {
//			  File f = new ClassPathResource("").getFile();
//		      final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "image");
//
//		      if (!Files.exists(path)) {
//		        Files.createDirectories(path);
//		      }
//
//		      Path filePath = path.resolve(file.getOriginalFilename());
//		      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//		      String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//		          .path("/image/")
//		          .path(file.getOriginalFilename())
//		          .toUriString();
//
//		      var result = Map.of(
//		          "filename", file.getOriginalFilename(),
//		          "fileUri", fileUri
//		      );
			responseMessage.setData("heelo");
			responseMessage.setMessage("Get Image");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
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
