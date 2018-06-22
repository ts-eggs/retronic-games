package com.retronic.security.entities;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class SecurityUserRoles implements GrantedAuthority, Serializable {

	private String authority;

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}
}
