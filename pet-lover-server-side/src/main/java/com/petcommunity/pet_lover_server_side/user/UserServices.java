package com.petcommunity.pet_lover_server_side.user;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
	
//	@Autowired
//	private UserRepository userRepository;
	
	public void storeUserDetails(User user) {
		System.out.println(user);
//		userRepository.save(user);
	}
}
