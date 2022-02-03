package com.learning.payload.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  @ApiModelProperty(position = 0)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  @ApiModelProperty(position = 1)
  private String email;

  @ApiModelProperty(position = 2)
  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  @ApiModelProperty(position = 3)
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
