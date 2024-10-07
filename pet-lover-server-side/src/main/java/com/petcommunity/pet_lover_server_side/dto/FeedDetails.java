package com.petcommunity.pet_lover_server_side.dto;

import java.io.File;

public class FeedDetails {
	Long id;
	String caption;
	File image; 
	
	public FeedDetails(String caption) {
		super();
		this.caption = caption;
	}

	public FeedDetails(Long id, String caption, File image) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "FeedDetails [id=" + id + ", caption=" + caption + ", image=" + image + "]";
	}

	
}
