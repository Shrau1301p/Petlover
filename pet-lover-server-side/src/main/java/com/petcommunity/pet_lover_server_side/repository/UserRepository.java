package com.petcommunity.pet_lover_server_side.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcommunity.pet_lover_server_side.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
