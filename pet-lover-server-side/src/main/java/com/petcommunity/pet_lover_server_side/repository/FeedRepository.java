package com.petcommunity.pet_lover_server_side.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petcommunity.pet_lover_server_side.model.Feed;
import com.petcommunity.pet_lover_server_side.model.User;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long>{

	Optional<List<Feed>> findByUserId(Long id);
}
