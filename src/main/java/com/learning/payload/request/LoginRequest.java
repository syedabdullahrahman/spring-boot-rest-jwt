package com.learning.payload.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequest {
	@NotBlank(message = "Username can't be blank")
	@ApiModelProperty(position = 0)
	private String username;

	@NotBlank(message = "Password can't be blank")
	@ApiModelProperty(position = 1)
	private String password;

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
