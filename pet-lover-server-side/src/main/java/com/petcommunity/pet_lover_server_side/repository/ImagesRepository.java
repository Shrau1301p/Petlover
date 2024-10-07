package com.petcommunity.pet_lover_server_side.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petcommunity.pet_lover_server_side.model.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long>{

}
