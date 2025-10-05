package com.petcommunity.pet_lover_server_side.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcommunity.pet_lover_server_side.dto.FeedDetails;
import com.petcommunity.pet_lover_server_side.dto.ResponseMessage;
import com.petcommunity.pet_lover_server_side.model.Feed;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.service.FeedServices;

@RestController
@RequestMapping("/feed")
public class FeedController {
	
	private FeedServices feedServices;
	
	public FeedController(FeedServices feedServices) {
		super();
		this.feedServices = feedServices;
	}

	@PostMapping("/add-feed")
	private ResponseEntity<ResponseMessage<Feed>> insertFeed(@RequestBody FeedDetails feed){
		ResponseMessage<Feed> responseMessage = new ResponseMessage<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        User currentUser = (User) authentication.getPrincipal();
			responseMessage.setData(feedServices.insertFeed(feed, currentUser.getId()));
			responseMessage.setMessage("Posted Successfully");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
		}catch (Exception e) {
			// TODO: handle exception
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	@GetMapping("/show-feeds")
	private ResponseEntity<ResponseMessage<List<Feed>>> getAllFeeds(){
		ResponseMessage<List<Feed>> responseMessage = new ResponseMessage<>();
		try {
			responseMessage.setData(feedServices.showFeeds());
//	        responseMessage.setMessage("Authentication Successfull");
	        responseMessage.setStatusCode(HttpStatus.OK.value());
	        return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			responseMessage.setError(e.toString());
	        responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	@GetMapping("/user-feed")
	private ResponseEntity<ResponseMessage<List<Feed>>> showFeedByUser(){
		ResponseMessage<List<Feed>> responseMessage = new ResponseMessage<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        User currentUser = (User) authentication.getPrincipal();
			responseMessage.setData(feedServices.getFeedsByUserId(currentUser.getId()));
			responseMessage.setMessage(null);
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
