package com.poc.frontend.dto;

import java.io.Serializable;

import base.enumtype.CrudMode;

public class UserDto implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String name;
	private String password;
	private String role;

	private CrudMode itemMode;

	@Override
	public UserDto clone() throws CloneNotSupportedException {
		return (UserDto) super.clone();
	}

	public UserDto() {

	}

	public UserDto(String email, String name, String password, String role) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CrudMode getItemMode() {
		return itemMode;
	}

	public void setItemMode(CrudMode itemMode) {
		this.itemMode = itemMode;
	}

}