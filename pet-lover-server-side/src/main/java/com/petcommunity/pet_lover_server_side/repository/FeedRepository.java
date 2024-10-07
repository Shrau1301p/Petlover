package com.petcommunity.pet_lover_server_side.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petcommunity.pet_lover_server_side.model.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long>{

}
