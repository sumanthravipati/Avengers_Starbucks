package com.avengers.starbucks.dto;

import java.io.Serializable;

public class StarbucksOutputMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String successResonse;
	private String errorResponse;
	
	public String getSuccessResonse() {
		return successResonse;
	}
	public void setSuccessResonse(String successResonse) {
		this.successResonse = successResonse;
	}
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
}
