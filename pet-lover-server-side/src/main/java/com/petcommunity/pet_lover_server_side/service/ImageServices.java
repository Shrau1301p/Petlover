package com.petcommunity.pet_lover_server_side.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.petcommunity.pet_lover_server_side.model.Images;
import com.petcommunity.pet_lover_server_side.model.User;
import com.petcommunity.pet_lover_server_side.repository.ImagesRepository;
import com.petcommunity.pet_lover_server_side.repository.UserRepository;


@Service
public class ImageServices {
	
	@Autowired
	private ImagesRepository imagesRepository;

	@Autowired
	private UserRepository userRepository;
	
	public ImageServices(ImagesRepository imagesRepository, UserRepository userRepository) {
		super();
		this.imagesRepository = imagesRepository;
		this.userRepository = userRepository;
	}

	public Images uploadImage(MultipartFile file,Long user_id) throws IOException {
		Images imgImages = new Images();
		imgImages.setName(file.getOriginalFilename());
		imgImages.setType(file.getContentType());
//		imgImages.setImageData(ImageUtils.compressImage(file.getBytes()));
		Images resultImages = imagesRepository.save(imgImages);
		
		User user = userRepository.findById(user_id).orElseThrow();
		user.setAavtar(resultImages);
		userRepository.save(user);
		return resultImages;
	}

}
