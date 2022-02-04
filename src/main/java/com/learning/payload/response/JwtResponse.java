package com.learning.payload.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class JwtResponse {
  @ApiModelProperty(value = "JWT Token",
          name = "token",
          dataType = "String",
          example = "")
  private String token;
  private String type = "Bearer";
  @ApiModelProperty(value = "User ID",
          name = "id",
          dataType = "Long",
          example = "")
  private Long id;
  @ApiModelProperty(value = "Username",
          name = "username",
          dataType = "String",
          example = "")
  private String username;
  @ApiModelProperty(value = "User email",
          name = "email",
          dataType = "String",
          example = "")
  private String email;
  @ApiModelProperty(value = "Roles",
          name = "roles",
          dataType = "List<String>",
          example = "")
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
