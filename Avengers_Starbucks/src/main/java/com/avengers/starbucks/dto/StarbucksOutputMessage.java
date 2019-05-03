package com.avengers.starbucks.dto;

import java.io.Serializable;

public class StarbucksOutputMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  private String successResponse;
  private String errorResponse;

  public String getSuccessResponse() {
    return successResponse;
  }

  public void setSuccessResponse(String successResponse) {
    this.successResponse = successResponse;
  }

  public String getErrorResponse() {
    return errorResponse;
  }

  public void setErrorResponse(String errorResponse) {
    this.errorResponse = errorResponse;
  }
}
