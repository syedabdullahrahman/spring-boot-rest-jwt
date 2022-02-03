package com.learning.payload.response;

import io.swagger.annotations.ApiModelProperty;

public class MessageResponse {
  @ApiModelProperty(position = 0)
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
