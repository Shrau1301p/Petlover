package com.petcommunity.pet_lover_server_side.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_lover_server_side.dto.FeedDetails;
import com.petcommunity.pet_lover_server_side.model.Feed;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.FeedRepository;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;

@Service
public class FeedServices {

	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Feed insertFeed(FeedDetails input,Long user_id) {
		Feed feed = new Feed();
		User user = userRepository.findById(user_id).orElseThrow();
		feed.setCaption(input.getCaption());
		feed.setUser(user);
		return feedRepository.save(feed);
	}
	
	public List<Feed> showFeeds(){
		return feedRepository.findAll();
	}
	
	public List<Feed> getFeedsByUserId(Long user_id){
		return feedRepository.findByUserId(user_id).orElseThrow();
	}
}
