package com.petcommunity.pet_lover_server_side.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petcommunity.pet_lover_server_side.model.User;

public class UserDetailsImpI implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	
	@JsonIgnore
	private String password;

	public UserDetailsImpI(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public static UserDetailsImpI build(User user) {
		return new UserDetailsImpI(
				user.getId(), 
				user.getusername(),
				user.getPassword());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpI user = (UserDetailsImpI) o;
		return Objects.equals(id, user.id);
	}
}
