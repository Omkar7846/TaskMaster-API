package com.TaskMaster.TaskMaster.Model.DTO;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

	public LoginRequestDTO(@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Password is required") String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}