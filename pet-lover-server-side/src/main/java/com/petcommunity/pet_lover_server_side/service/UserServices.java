package com.petcommunity.pet_lover_server_side.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petcommunity.pet_lover_server_side.dto.UserRequest;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;

@Service
public class UserServices{
	
	@Autowired
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;

	public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}
	
	 public User signup(UserRequest input) {
	        User user = new User();
	        user.setUsername(input.getUsername());
	        user.setPassword(passwordEncoder.encode(input.getPassword()));
	        return userRepository.save(user);
	 }

	    public User authenticate(UserRequest input) {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        input.getUsername(),
	                        input.getPassword()
	                )
	        );

	        return userRepository.findByUsername(input.getUsername())
	                .orElseThrow();
	    }

}
