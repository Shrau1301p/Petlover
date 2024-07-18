package com.petcommunity.pet_lover_server_side.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "\"user\"" , uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
	
	@Id
	@Nonnull
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Nonnull
	private String username;
	
	@Nonnull
	private String password;
	
	 @CreationTimestamp
	 @Column(updatable = false, name = "created_at")
	 private Date createdAt;

	 @UpdateTimestamp
	 @Column(name = "updated_at")
	 private Date updatedAt;
	
	public User() {
	}
	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getusername() {
		return username;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
