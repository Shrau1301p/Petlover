package com.petcommunity.pet_lover_server_side.dto;

import java.io.File;
import java.util.Date;

public class ProfileDetails {
	Long id;
	String pet_name;
	Date dob_date;
	String category;
	String describtion;
	File avatar;
	
	public ProfileDetails(Long id,String pet_name, Date dob_date,String category, String describtion, File avatar) {
		super();
		this.id = id;
		this.pet_name = pet_name;
		this.dob_date = dob_date;
		this.category = category;
		this.describtion = describtion;
		this.avatar = avatar;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameString() {
		return pet_name;
	}

	public void setNameString(String pet_name) {
		this.pet_name = pet_name;
	}

	public Date getDobString() {
		return dob_date;
	}

	public void setDobString(Date dob_date) {
		this.dob_date = dob_date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescribtionString() {
		return describtion;
	}

	public void setDescribtionString(String describtion) {
		this.describtion = describtion;
	}

	public File getImage() {
		return avatar;
	}

	public void setImage(File avatar) {
		this.avatar = avatar;
	}


	@Override
	public String toString() {
		return "ProfileDetails [id=" + id + ", pet_name=" + pet_name + ", dob_date=" + dob_date + ", category="
				+ category + ", describtion=" + describtion + ", avatar=" + avatar + "]";
	}

	
	
}
