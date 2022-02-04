package com.learning.payload.response;

import io.swagger.annotations.ApiModelProperty;

public class MessageResponse {
  @ApiModelProperty(value = "Response message",
          name = "message",
          dataType = "String",
          example = "User registration successful")
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
